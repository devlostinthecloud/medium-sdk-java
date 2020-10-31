package com.devlostncloud.medium;

public enum PublishStatus {
    PUBLIC("public"), DRAFT("draft"), UNLISTED("unlisted");

    private final String value;

    PublishStatus(String value) {
        this.value = value;
    }

    String value() {
        return this.value;
    }

    static PublishStatus from(String value) {
        PublishStatus[] statuses = PublishStatus.values();
        for (PublishStatus status : statuses) {
            if (status.value.equals(value)) {
                return status;
            }
        }
        return null;
    }
}
