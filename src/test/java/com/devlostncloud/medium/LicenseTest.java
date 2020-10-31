package com.devlostncloud.medium;

import org.junit.jupiter.api.Test;

import static com.devlostncloud.medium.License.ALL_RIGHTS_RESERVED;
import static com.devlostncloud.medium.License.CC_40_BY;
import static com.devlostncloud.medium.License.CC_40_BY_NC;
import static com.devlostncloud.medium.License.CC_40_BY_NC_ND;
import static com.devlostncloud.medium.License.CC_40_BY_NC_SA;
import static com.devlostncloud.medium.License.CC_40_BY_ND;
import static com.devlostncloud.medium.License.CC_40_BY_SA;
import static com.devlostncloud.medium.License.CC_40_ZERO;
import static com.devlostncloud.medium.License.PUBLIC_DOMAIN;
import static org.assertj.core.api.Assertions.assertThat;

class LicenseTest {

    @Test
    void values() {
        assertThat(License.values()).containsExactly(
                ALL_RIGHTS_RESERVED,
                PUBLIC_DOMAIN,
                CC_40_BY,
                CC_40_BY_SA,
                CC_40_BY_ND,
                CC_40_BY_NC,
                CC_40_BY_NC_ND,
                CC_40_BY_NC_SA,
                CC_40_ZERO);
    }

    @Test
    public void shouldReturnAllRightsReservedLicenseValue() {
        assertThat(ALL_RIGHTS_RESERVED.value()).isEqualTo("all-rights-reserved");
    }

    @Test
    public void shouldReturnPublicDomainLicenseValue() {
        assertThat(PUBLIC_DOMAIN.value()).isEqualTo("public-domain");
    }

    /**
     * Creative Commons
     * https://creativecommons.org/licenses/
     */
    @Test
    public void shouldReturnCreativeCommonsAttributionLicenseValue() {
        assertThat(CC_40_BY.value()).isEqualTo("cc-40-by");
    }

    @Test
    public void shouldReturnCreativeCommonsAttributionShareAlikeLicenseValue() {
        assertThat(CC_40_BY_SA.value()).isEqualTo("cc-40-by-sa");
    }

    @Test
    public void shouldReturnCreativeCommonsAttributionNoDerivsLicenseValue() {
        assertThat(CC_40_BY_ND.value()).isEqualTo("cc-40-by-nd");
    }

    @Test
    public void shouldReturnCreativeCommonsAttributionNonCommercialLicenseValue() {
        assertThat(CC_40_BY_NC.value()).isEqualTo("cc-40-by-nc");
    }

    @Test
    public void shouldReturnCreativeCommonsAttributionAttributionNonCommercialNoDerivsLicenseValue() {
        assertThat(CC_40_BY_NC_ND.value()).isEqualTo("cc-40-by-nc-nd");
    }

    @Test
    public void shouldReturnCreativeCommonsAttributionAttributionNonCommercialShareAlikeLicenseValue() {
        assertThat(CC_40_BY_NC_SA.value()).isEqualTo("cc-40-by-nc-sa");
    }

    @Test
    public void shouldReturnCreativeCommonsAttributionZeroLicenseValue() {
        assertThat(CC_40_ZERO.value()).isEqualTo("cc-40-zero");
    }

    @Test
    public void shouldReturnLicenseFromValue() {
        assertThat(License.from("cc-40-by-nc-sa")).isEqualTo(CC_40_BY_NC_SA);
    }

    @Test
    public void shouldReturnNullAsLicenseFromNonRecognisedValue() {
        assertThat(License.from("something-else")).isNull();
    }
}