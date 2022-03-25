package stander.stander.model;

public class MemberForm {

        private Long id;
        private String username;
        private String name;
        private String password;
        private String phonenum;
        private String personnum_front;
        private String personnum_back;


//    private String created;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhonenum() {
            return phonenum;
        }

        public void setPhonenum(String phonenum) {
            this.phonenum = phonenum;
        }
    public String getPersonnum_front() {
        return personnum_front;
    }

    public void setPersonnum_front(String personnum_front) {
        this.personnum_front = personnum_front;
    }

    public String getPersonnum_back() {
        return personnum_back;
    }

    public void setPersonnum_back(String personnum_back) {
        this.personnum_back = personnum_back;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public String getCreated() {
//            return created;
//        }
//
//        public void setCreated(String created) {
//            this.created = created;
//        }
}
