package com.devlostncloud.medium;

public enum License {
    ALL_RIGHTS_RESERVED("all-rights-reserved"),
    PUBLIC_DOMAIN("public-domain"),
    CC_40_BY("cc-40-by"),
    CC_40_BY_SA("cc-40-by-sa"),
    CC_40_BY_ND("cc-40-by-nd"),
    CC_40_BY_NC("cc-40-by-nc"),
    CC_40_BY_NC_ND("cc-40-by-nc-nd"),
    CC_40_BY_NC_SA("cc-40-by-nc-sa"),
    CC_40_ZERO("cc-40-zero");

    private final String value;

    License(String value) {
        this.value = value;
    }

    String value() {
        return value;
    }

    static License from(String value) {
        License[] licenses = License.values();
        for (License license : licenses) {
            if (license.value.equals(value)) {
                return license;
            }
        }
        return null;
    }
}
