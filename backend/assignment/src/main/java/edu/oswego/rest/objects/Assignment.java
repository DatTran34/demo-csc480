package edu.oswego.rest.objects;

import java.util.Date;

public class Assignment {

        //private final DatabaseInterface dbi = new DatabaseInterface();
        private int assignmentID;
        private int courseID;
        private  String pdfDoc;
        private String settings;


        /**
         * This constructor is used to generate a assignment that does not yet exist in the database.
         * The assignment does not insert itself into the database, as a case may exist where a assignment
         * is created, but does not need to be inserted.
         * @param courseID The unique integer that is the course ID. 0 if new course.
         * @param pdfDoc The content provided for the assignment.
         * @param assignmentID The unique integer that is the assignment ID. 0 if new assignment.
         * @param settings A string representation of the settings for the assignment.
         */
        public Assignment(int assignmentID, String pdfDoc, String settings,int courseID){
                this.courseID = courseID;
                this.pdfDoc = pdfDoc;
                this.settings = settings;
                this.assignmentID = assignmentID;
        }

        public Assignment(){
                this.assignmentID = 1;
                this.courseID = 0;
                this.pdfDoc = "";
                this.settings = "";
        }

        public int getAssignmentID() {
                return assignmentID;
        }

        public int getCourseID() {
                return courseID;
        }

        public String getPdfDoc() {
                return pdfDoc;
        }

        public void setAssignmentID(int assignmentID) {
                this.assignmentID = assignmentID;
        }

        public void setCourseID(int courseID) {
                this.courseID = courseID;
        }

        public void setPdfDoc(String pdfDoc) {
                this.pdfDoc = pdfDoc;
        }

        public void setSettings(String settings) {
                this.settings = settings;
        }
        /**
         * This returns the entire set of user settings.
         * @return The string that represents the user settings.
         */
        public String getSettings(){
                return settings;
        }

        /**
         * This method returns the character representing the user setting at the index given.
         * @param index The index from which the setting is to be retrieved.
         * @return The character representing the user setting.
         */
        public char getSetting(int index){
                if(index < settings.length()) return settings.charAt(index);
                else return 0;
        }

        /**
         * This method updates the settings for a course.
         * @param index The index at which the setting is in the string.
         * @param setting The updated setting.
         * @return True upon completion.
         */
        public boolean updateSetting(int index, char setting){
                String firstHalf = "";
                if(index-1>0) firstHalf = settings.substring(0, index-1);
                String lastHalf = "";
                if(index+1<settings.length()) lastHalf = settings.substring(index+1);
                settings = firstHalf+setting+lastHalf;
                return true;
        }

        /**
         * This method updates all the settings for a course.
         * @param settings The new settings string.
         * @return True upon completion.
         */
        public boolean updateAllSettings(String settings){
                this.settings = settings;
                return true;
        }

        /**
         * This method will generate a unique assignment ID so long as we have less than
         * Integer.MAX_VALUE courses at once. Otherwise, this will become a problem.
         * @return The ID is generated by the native Java hash code of the name+code string.
         */
        public int generateAssignmentID(){
//                String a =  "assignment";
//                int pid = a.hashCode();
//                if(dbi.getCourse(pid)!=null){
//                        while(dbi.getCourse(pid)!=null){
//                                pid++;
//                                if(pid==Integer.MAX_VALUE) pid = 1;
//                        }
//                }
//                return pid;
                return -1;
        }
}

