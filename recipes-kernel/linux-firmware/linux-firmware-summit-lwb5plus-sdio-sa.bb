SUMMARY = "Firmware files for LWB5+ module"
SECTION = "kernel"

LICENSE = "\
    Firmware-cypress \
    & Firmware-ezurio \
"

NO_GENERIC_LICENSE[Firmware-cypress] = "LICENSE.cypress"
NO_GENERIC_LICENSE[Firmware-ezurio] = "LICENSE.ezurio"

LIC_FILES_CHKSUM = " \
    file://LICENSE.cypress;md5=cbc5f665d04f741f1e006d2096236ba7 \
    file://LICENSE.ezurio;md5=fd3dd0630b215465b6f50540642d5b93 \
"

SRC_URI = " \
    https://github.com/Ezurio/SonaIF-Release-Packages/releases/download/LRD-REL-12.103.0.5/summit-lwb5plus-sdio-sa-firmware-12.103.0.5.tar.bz2;subdir=summit \
"
SRC_URI[sha256sum] = "ffd17719091cf4d9b5fc3c03ae144dee87c2d7ff76b68a0d0662ff13f8438bb8"
S = "${WORKDIR}/summit"

inherit allarch

CLEANBROKEN = "1"

do_compile() {
    :
}

do_install() {
    install -d ${D}${nonarch_base_libdir}/firmware/brcm

    find ${S}/lib/firmware/brcm \
        -exec install -m 644 {} ${D}${nonarch_base_libdir}/firmware/brcm \;
    if [ ! -z "${LWB_REGDOMAIN}" ]; then
        install -d  "${D}${sysconfdir}/modprobe.d"
        echo "options brcmfmac regdomain=\"${LWB_REGDOMAIN}\"" > "${D}${sysconfdir}/modprobe.d/brcmfmac_regd.conf"
    fi

    install -m 644 ${S}/LICENSE.cypress ${D}${nonarch_base_libdir}/firmware/brcm
    install -m 644 ${S}/LICENSE.ezurio ${D}${nonarch_base_libdir}/firmware/brcm
}

FILES:${PN} = " \
    ${nonarch_base_libdir}/firmware/brcm/* \
    ${sysconfdir} \
"
