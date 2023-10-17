package com.cuckoofi.pricelineservice.model.testmodel;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HotelAutoSuggestResponse {
    @JsonProperty("getHotelAutoSuggestV2")
    private HotelAutoSuggestV2 getHotelAutoSuggestV2;

    public HotelAutoSuggestV2 getGetHotelAutoSuggestV2() {
        return getHotelAutoSuggestV2;
    }

    public void setGetHotelAutoSuggestV2(HotelAutoSuggestV2 getHotelAutoSuggestV2) {
        this.getHotelAutoSuggestV2 = getHotelAutoSuggestV2;
    }

    public static class HotelAutoSuggestV2 {
        private Results results;

        public Results getResults() {
            return results;
        }

        public void setResults(Results results) {
            this.results = results;
        }
    }

    public static class Results {
        private Status status;
        private Result result;
        private String time;

        public Status getStatus() {
            return status;
        }

        public void setStatus(Status status) {
            this.status = status;
        }

        public Result getResult() {
            return result;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class Status {
        private String status;
        private int status_code;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public int getStatus_code() {
            return status_code;
        }

        public void setStatus_code(int status_code) {
            this.status_code = status_code;
        }
    }

    public static class Result {
        private City[] cities;

        public City[] getCities() {
            return cities;
        }

        public void setCities(City[] cities) {
            this.cities = cities;
        }
    }

    public static class City {
        // Define fields to map city data here.
        // You can use @JsonProperty annotations for mapping.
    }
}
