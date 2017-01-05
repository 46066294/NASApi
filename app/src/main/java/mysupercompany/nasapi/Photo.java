package mysupercompany.nasapi;

import java.io.Serializable;

/**
 * Created by Mat on 04/01/2017.
 */

public class Photo implements Serializable{

    private Integer id, sol, page, totalPhotos, maxSol;
    private String roverName, roverCam, imageUrl, status;
    private String landingDate, launchDate, maxDate;

    public Photo() {}

    public Photo(Integer id, Integer sol, Integer page, Integer totalPhotos, Integer maxSol,
                 String roverName, String roverCam, String imageUrl, String status,
                 String landingDate, String launchDate, String maxDate) {
        this.id = id;
        this.sol = sol;
        this.page = page;
        this.totalPhotos = totalPhotos;
        this.maxSol = maxSol;
        this.maxDate = maxDate;
        this.roverName = roverName;
        this.roverCam = roverCam;
        this.imageUrl = imageUrl;
        this.status = status;
        this.landingDate = landingDate;
        this.launchDate = launchDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSol() {
        return sol;
    }

    public void setSol(Integer sol) {
        this.sol = sol;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPhotos() {
        return totalPhotos;
    }

    public void setTotalPhotos(Integer totalPhotos) {
        this.totalPhotos = totalPhotos;
    }

    public Integer getMaxSol() {
        return maxSol;
    }

    public void setMaxSol(Integer maxSol) {
        this.maxSol = maxSol;
    }

    public String getRoverName() {
        return roverName;
    }

    public void setRoverName(String roverName) {
        this.roverName = roverName;
    }

    public String getRoverCam() {
        return roverCam;
    }

    public void setRoverCam(String roverCam) {
        this.roverCam = roverCam;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLandingDate() {
        return landingDate;
    }

    public void setLandingDate(String landingDate) {
        this.landingDate = landingDate;
    }

    public String getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(String launchDate) {
        this.launchDate = launchDate;
    }

    public String getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(String maxDate) {
        this.maxDate = maxDate;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", sol=" + sol +
                ", page=" + page +
                ", totalPhotos=" + totalPhotos +
                ", maxSol=" + maxSol +
                ", roverName='" + roverName + '\'' +
                ", roverCam='" + roverCam + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", status='" + status + '\'' +
                ", landingDate=" + landingDate +
                ", launchDate=" + launchDate +
                ", maxDate=" + maxDate +
                '}';
    }
}
