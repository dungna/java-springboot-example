package javabasic;

public class setterGetterJava {
    private String myField;

    /*
    Phương thức setter được sử dụng để đặt giá trị của một trường.
    Cú pháp của một setter method là setPropertyName, trong đó "PropertyName" là tên của trường mà bạn muốn đặt giá trị.
    Thông thường, một setter method sẽ có một tham số đầu vào để truyền giá trị mới cho trường.
    */
    public void setMyField(String value) {
        this.myField = value;
    }

    /*
    Phương thức getter được sử dụng để lấy giá trị của một trường.
    Cú pháp của một getter method là getPropertyName hoặc isPropertyName (đối với trường boolean), trong đó "PropertyName" là tên của trường mà bạn muốn lấy giá trị.
    Thông thường, một getter method sẽ không có tham số và trả về giá trị của trường.
     */
    public String getMyField() {
        return this.myField;
    }
}
