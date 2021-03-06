/*
 * 
 * Autopsy Forensic Browser
 * 
 * Copyright 2018 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.commonfilesearch;

import org.sleuthkit.autopsy.centralrepository.datamodel.CorrelationCase;
import org.sleuthkit.autopsy.centralrepository.datamodel.EamDb;
import org.sleuthkit.autopsy.centralrepository.datamodel.EamDbException;
import org.sleuthkit.autopsy.centralrepository.datamodel.CorrelationAttributeInstance.Type;

/**
 * Provides logic for selecting common files from all data sources and all cases
 * in the Central Repo.
 */
abstract class InterCaseCommonAttributeSearcher extends AbstractCommonAttributeSearcher {

    private final EamDb dbManager;
    /**
     * The Correlation Type to find matches on.
     */
    final Type corAttrType;

    /**
     * Implements the algorithm for getting common files across all data sources
     * and all cases. Can filter on mime types conjoined by logical AND.
     *
     * @param filterByMediaMimeType match only on files whose mime types can be
     * broadly categorized as media types
     * @param filterByDocMimeType match only on files whose mime types can be
     * broadly categorized as document types
     *
     * @throws EamDbException
     */
    InterCaseCommonAttributeSearcher(boolean filterByMediaMimeType, boolean filterByDocMimeType, Type corAttrType, int percentageThreshold) throws EamDbException {
        super(filterByMediaMimeType, filterByDocMimeType, percentageThreshold);
        dbManager = EamDb.getInstance();
        this.corAttrType = corAttrType;
    }

    protected CorrelationCase getCorrelationCaseFromId(int correlationCaseId) throws EamDbException {
        for (CorrelationCase cCase : this.dbManager.getCases()) {
            if (cCase.getID() == correlationCaseId) {
                return cCase;
            }
        }
        throw new IllegalArgumentException("Cannot locate case.");
    }
    
}
