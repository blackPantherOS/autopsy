/*
 * Autopsy Forensic Browser
 * 
 * Copyright 2011 Basis Technology Corp.
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

/*
 * KeywordSearchListImportExportForm.java
 *
 * Created on Feb 10, 2012, 4:04:13 PM
 */
package org.sleuthkit.autopsy.keywordsearch;

import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author dfickling
 */
class KeywordSearchListsManagementPanel extends javax.swing.JPanel {

    private Logger logger = Logger.getLogger(KeywordSearchListsManagementPanel.class.getName());
    private KeywordListTableModel tableModel;
    
    private static KeywordSearchListsManagementPanel instance = null;
    
    /** Creates new form KeywordSearchListImportExportForm */
    private KeywordSearchListsManagementPanel() {
        tableModel = new KeywordListTableModel();
        initComponents();
        customizeComponents();
    }
    
    public static synchronized KeywordSearchListsManagementPanel getDefault() {
        if (instance == null) {
            instance = new KeywordSearchListsManagementPanel();
        }
        return instance;
    }
    
    private void customizeComponents() {


        listsTable.setAutoscrolls(true);
        listsTable.setTableHeader(null);
        listsTable.setShowHorizontalLines(false);
        listsTable.setShowVerticalLines(false);

        listsTable.getParent().setBackground(listsTable.getBackground());

        listsTable.setCellSelectionEnabled(false);
        listsTable.setRowSelectionAllowed(true);
        tableModel.resync();

        KeywordSearchListsXML.getCurrent().addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals(KeywordSearchListsXML.ListsEvt.LIST_ADDED.toString())) {
                    tableModel.resync();
                    for(int i = 0; i<listsTable.getRowCount(); i++){
                            String name = (String) listsTable.getValueAt(i, 0);
                            if(((String) evt.getNewValue()).equals(name))
                                listsTable.getSelectionModel().setSelectionInterval(i, i);
                    }
                } else if (evt.getPropertyName().equals(KeywordSearchListsXML.ListsEvt.LIST_DELETED.toString())) {
                    tableModel.resync();
                    if(listsTable.getRowCount() > 0)
                        listsTable.getSelectionModel().setSelectionInterval(0, 0);
                    else
                        listsTable.getSelectionModel().clearSelection();
                } else if (evt.getPropertyName().equals(KeywordSearchListsXML.ListsEvt.LIST_UPDATED.toString())) {
                    tableModel.resync((String) evt.getNewValue()); //changed list name
                }
            }
        });

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listsTable = new javax.swing.JTable();
        newListButton = new javax.swing.JButton();
        importButton = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(200, 0));
        setPreferredSize(new java.awt.Dimension(200, 297));

        jScrollPane1.setPreferredSize(new java.awt.Dimension(200, 402));

        listsTable.setModel(tableModel);
        listsTable.setShowHorizontalLines(false);
        listsTable.setShowVerticalLines(false);
        listsTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(listsTable);

        newListButton.setBackground(new java.awt.Color(204, 204, 204));
        newListButton.setText(org.openide.util.NbBundle.getMessage(KeywordSearchListsManagementPanel.class, "KeywordSearchListsManagementPanel.newListButton.text")); // NOI18N
        newListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newListButtonActionPerformed(evt);
            }
        });

        importButton.setBackground(new java.awt.Color(204, 204, 204));
        importButton.setText(org.openide.util.NbBundle.getMessage(KeywordSearchListsManagementPanel.class, "KeywordSearchListsManagementPanel.importButton.text")); // NOI18N
        importButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(newListButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newListButton)
                    .addComponent(importButton)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newListButtonActionPerformed
        KeywordSearchEditListPanel.getDefault().save();
        KeywordSearchListsXML writer = KeywordSearchListsXML.getCurrent();
        String listName = (String) JOptionPane.showInputDialog(null, "New keyword list name:", "New Keyword List", JOptionPane.PLAIN_MESSAGE, null, null, "");
        if (listName == null || listName.trim().equals("")) {
            return;
        }
        boolean shouldAdd = false;
        if (writer.listExists(listName)) {
            boolean replace = KeywordSearchUtil.displayConfirmDialog("New Keyword List", "Keyword List <" + listName + "> already exists, do you want to replace it?", KeywordSearchUtil.DIALOG_MESSAGE_TYPE.WARN);
            if (replace) {
                shouldAdd = true;
            }
        } else {
            shouldAdd = true;
        }
        if (shouldAdd) {
            writer.addList(listName, new ArrayList<Keyword>());
        }
        for (int i = 0; i < listsTable.getRowCount(); i++) {
            if (listsTable.getValueAt(i, 0).equals(listName)) {
                listsTable.getSelectionModel().addSelectionInterval(i, i);
            }
        }
    }//GEN-LAST:event_newListButtonActionPerformed

    private void importButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importButtonActionPerformed
        KeywordSearchEditListPanel.getDefault().save();
        final String FEATURE_NAME = "Keyword List Import";

        JFileChooser chooser = new JFileChooser();
        final String EXTENSION = "xml";
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Keyword List XML file", EXTENSION);
        chooser.setFileFilter(filter);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        int returnVal = chooser.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File selFile = chooser.getSelectedFile();
            if (selFile == null) {
                return;
            }

            //force append extension if not given
            String fileAbs = selFile.getAbsolutePath();

            final KeywordSearchListsXML reader = new KeywordSearchListsXML(fileAbs);
            if (!reader.load()) {
                KeywordSearchUtil.displayDialog(FEATURE_NAME, "Error importing keyword list from file " + fileAbs, KeywordSearchUtil.DIALOG_MESSAGE_TYPE.ERROR);
                return;
            }

            List<KeywordSearchList> toImport = reader.getListsL();
            List<KeywordSearchList> toImportConfirmed = new ArrayList<KeywordSearchList>();

            final KeywordSearchListsXML writer = KeywordSearchListsXML.getCurrent();

            for (KeywordSearchList list : toImport) {
                //check name collisions
                if (writer.listExists(list.getName())) {
                    Object[] options = {"Yes, overwrite",
                        "No, skip",
                        "Cancel import"};
                    int choice = JOptionPane.showOptionDialog(this,
                            "Keyword list <" + list.getName() + "> already exists locally, overwrite?",
                            "Import list conflict",
                            JOptionPane.YES_NO_CANCEL_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if (choice == JOptionPane.OK_OPTION) {
                        toImportConfirmed.add(list);
                    } else if (choice == JOptionPane.CANCEL_OPTION) {
                        break;
                    }

                } else {
                    //no conflict
                    toImportConfirmed.add(list);
                }

            }

            if (toImportConfirmed.isEmpty()) {
                return;
            }

            if (writer.writeLists(toImportConfirmed)) {
                KeywordSearchUtil.displayDialog(FEATURE_NAME, "Keyword list imported", KeywordSearchUtil.DIALOG_MESSAGE_TYPE.INFO);
            }

        }
    }//GEN-LAST:event_importButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton importButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable listsTable;
    private javax.swing.JButton newListButton;
    // End of variables declaration//GEN-END:variables

    
    private class KeywordListTableModel extends AbstractTableModel {
        //data

        private KeywordSearchListsXML listsHandle = KeywordSearchListsXML.getCurrent();
        private Set<TableEntry> listData = new TreeSet<TableEntry>();

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public int getRowCount() {
            return listData.size();
        }

        @Override
        public String getColumnName(int column) {
            return "Name";
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            TableEntry entry = null;
            //iterate until row
            Iterator<TableEntry> it = listData.iterator();
            for (int i = 0; i <= rowIndex; ++i) {
                entry = it.next();
            }
            return (Object) entry.name;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            throw new UnsupportedOperationException("Editing of cells is not supported");
        }

        @Override
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        List<String> getAllLists() {
            List<String> ret = new ArrayList<String>();
            for (TableEntry e : listData) {
                ret.add(e.name);
            }
            return ret;
        }

        List<String> getSelectedLists(int[] selected) {
            List<String> ret = new ArrayList<String>();
            for(int i = 0; i < selected.length; i++){
                ret.add((String) getValueAt(0, selected[i]));
            }
            return ret;
        }

        boolean listExists(String list) {
            List<String> all = getAllLists();
            return all.contains(list);
        }

        //delete selected from handle, events are fired from the handle
        void deleteSelected(int[] selected) {
            List<String> toDel = new ArrayList<String>();
            for(int i = 0; i < selected.length; i++){
                toDel.add((String) getValueAt(0, selected[i]));
            }
            for (String del : toDel) {
                listsHandle.deleteList(del);
            }

        }

        //resync model from handle, then update table
        void resync() {
            listData.clear();
            addLists(listsHandle.getListsL());
            fireTableDataChanged();
        }

        //resync single model entry from handle, then update table
        void resync(String listName) {
            TableEntry found = null;
            for (TableEntry e : listData) {
                if (e.name.equals(listName)) {
                    found = e;
                    break;
                }
            }
            if (found != null) {
                listData.remove(found);
                addList(listsHandle.getList(listName));
            }
            fireTableDataChanged();
        }

        //add list to the model
        private void addList(KeywordSearchList list) {
            if (!listExists(list.getName())) {
                listData.add(new TableEntry(list));
            }
        }

        //add lists to the model
        private void addLists(List<KeywordSearchList> lists) {
            for (KeywordSearchList list : lists) {
                if (!listExists(list.getName())) {
                    listData.add(new TableEntry(list));
                }
            }
        }

        //single model entry
        class TableEntry implements Comparable {

            String name;

            TableEntry(KeywordSearchList list) {
                this.name = list.getName();
            }

            @Override
            public int compareTo(Object o) {
                return this.name.compareTo(((TableEntry) o).name);
            }
        }
    }
    
    void addListSelectionListener(ListSelectionListener l) {
        listsTable.getSelectionModel().addListSelectionListener(l);
    }
    
    List<String> getAllLists() {
        return tableModel.getAllLists();
    }
}
