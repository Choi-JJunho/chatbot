package dto;

public class MenuDTO {

    private String korean;
    private String special;
    private String onedish;
    private String western;
    private String faculty;
    private String subak;

    public String getKorean() {
        return korean;
    }

    public void setKorean(String korean) {
        this.korean = korean;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getOnedish() {
        return onedish;
    }

    public void setOnedish(String onedish) {
        this.onedish = onedish;
    }

    public String getWestern() {
        return western;
    }

    public void setWestern(String western) {
        this.western = western;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getSubak() {
        return subak;
    }

    public void setSubak(String subak) {
        this.subak = subak;
    }


    @Override
    public String toString() {
        return "MenuDTO{" +
                "korean='" + korean + '\'' +
                ", special='" + special + '\'' +
                ", onedish='" + onedish + '\'' +
                ", western='" + western + '\'' +
                ", faculty='" + faculty + '\'' +
                ", subak='" + subak + '\'' +
                '}';
    }
}
