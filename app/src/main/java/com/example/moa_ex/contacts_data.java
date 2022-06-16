package com.example.moa_ex;

public class contacts_data {

    private int imgId;
    private String name;
    private String phone;

    public contacts_data(int imgId, String name, String phone){
        this.imgId = imgId;
        this.name = name;
        this.phone = phone;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    // 객체변수 내에 있는 데이터를 한번에 출력할 때 활용
    // ex)
    // Person person = new Person("홍길동", 25);
    // syso(person); --> Person("name=홍길동","age=25"}; 형태로 출력
    @Override
    public String toString() {
        return "ContactVO{" +
                "imgId=" + imgId +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
