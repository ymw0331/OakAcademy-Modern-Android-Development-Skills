package com.wayneyong.information_book;

public class ModelClass {

    //name and category, picture of category
    private String imageName;
    private String categoryName;

    public ModelClass(String imageName, String categoryName) {
        this.imageName = imageName;
        this.categoryName = categoryName;
    }

    public String getImageName() {
        return imageName;
    }

    public String getCategoryName() {
        return categoryName;
    }




}
