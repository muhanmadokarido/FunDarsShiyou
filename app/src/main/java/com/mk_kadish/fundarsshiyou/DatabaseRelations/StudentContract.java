package com.mk_kadish.fundarsshiyou.DatabaseRelations;

public class StudentContract
{
    private StudentContract(){}

    public static class studentEntry
    {
        public static final String table_name="users_info";
        public static final String user_id="user_id";
        public static final String user_name="user_userName";
        public static final String user_type="user_userType";
        public static final String user_password="user_userPassword";
        public static final String user_schoolId="user_SchoolId";
        public static final String studentPoints="student_points";

    }
}
