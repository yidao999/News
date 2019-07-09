package com.example.myapplication.mvp.mvp_video.model;

import java.util.List;

/**
 * author: 小川
 * Date: 2019/5/16
 * Description:
 */
public class VideoModel {


    private List<TrailersBean> trailers;

    public List<TrailersBean> getTrailers() {
        return trailers;
    }

    public void setTrailers(List<TrailersBean> trailers) {
        this.trailers = trailers;
    }

    public static class TrailersBean {
        /**
         * id : 74704
         * movieName : 迪士尼《沉睡魔咒2》预告片
         * coverImg : http://img5.mtime.cn/mg/2019/05/14/091528.78106221_120X90X4.jpg
         * movieId : 225794
         * url : http://vfx.mtime.cn/Video/2019/05/14/mp4/190514144610105901.mp4
         * hightUrl : http://vfx.mtime.cn/Video/2019/05/14/mp4/190514144610105901.mp4
         * videoTitle : 沉睡魔咒2 预告片
         * videoLength : 84
         * rating : -1
         * type : ["冒险","家庭","奇幻"]
         * summary :
         */

        private int id;
        private String movieName;
        private String coverImg;
        private int movieId;
        private String url;
        private String hightUrl;
        private String videoTitle;
        private long videoLength;
        private String rating;
        private String summary;
        private List<String> type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getMovieName() {
            return movieName;
        }

        public void setMovieName(String movieName) {
            this.movieName = movieName;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public int getMovieId() {
            return movieId;
        }

        public void setMovieId(int movieId) {
            this.movieId = movieId;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getHightUrl() {
            return hightUrl;
        }

        public void setHightUrl(String hightUrl) {
            this.hightUrl = hightUrl;
        }

        public String getVideoTitle() {
            return videoTitle;
        }

        public void setVideoTitle(String videoTitle) {
            this.videoTitle = videoTitle;
        }

        public long getVideoLength() {
            return videoLength;
        }

        public void setVideoLength(long videoLength) {
            this.videoLength = videoLength;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public List<String> getType() {
            return type;
        }

        public void setType(List<String> type) {
            this.type = type;
        }
    }
}
