package com.cxsz.elu.main.model.bean;

public class LocationUploadBean {

    /**
     * id : 33
     * version : 1.0
     * params : {"$GeoLocation":{"value":{"latitude":26,"longitude":28}}}
     */

    private String id;
    private String version;
    private ParamsBean params;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ParamsBean getParams() {
        return params;
    }

    public void setParams(ParamsBean params) {
        this.params = params;
    }

    public static class ParamsBean {
        /**
         * $GeoLocation : {"value":{"latitude":26,"longitude":28}}
         */

        private $GeoLocationBean $GeoLocation;

        public $GeoLocationBean get$GeoLocation() {
            return $GeoLocation;
        }

        public void set$GeoLocation($GeoLocationBean $GeoLocation) {
            this.$GeoLocation = $GeoLocation;
        }

        public static class $GeoLocationBean {
            /**
             * value : {"latitude":26,"longitude":28}
             */

            private ValueBean value;

            public ValueBean getValue() {
                return value;
            }

            public void setValue(ValueBean value) {
                this.value = value;
            }

            public static class ValueBean {
                /**
                 * latitude : 26
                 * longitude : 28
                 */

                private double latitude;
                private double longitude;

                public double getLatitude() {
                    return latitude;
                }

                public void setLatitude(double latitude) {
                    this.latitude = latitude;
                }

                public double getLongitude() {
                    return longitude;
                }

                public void setLongitude(double longitude) {
                    this.longitude = longitude;
                }
            }
        }
    }
}
