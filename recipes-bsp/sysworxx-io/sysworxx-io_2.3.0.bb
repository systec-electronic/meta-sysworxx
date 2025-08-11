SUMMARY = "I/O library for accessing basic peripherals (digital/analog I/O's, watchdog, etc) on sysWORXX devices"
HOMEPAGE = "http://www.systec-electronic.com"
LICENSE = "LGPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c51d3eef3be114124d11349ca0d7e117"

inherit cargo cargo-update-recipe-crates
require ${BPN}-git.inc
require ${BPN}-crates.inc

inherit systemd python3-dir python_pyo3

DEPENDS = "lmsensors libiio python3"

RDEPENDS:${PN} = "lmsensors libiio"
RDEPENDS:${PN}-codesys-connector += "${PN} sudo"
RDEPENDS:${PN}-js += "nodejs"
RDEPENDS:${PN}-py += "python3"
RDEPENDS:${PN}-dev += "${PN}"

PACKAGES += " \
    ${PN}-js \
    ${PN}-py \
    ${PN}-codesys-connector \
"

do_configure() {
    cargo_common_do_configure
    python_pyo3_do_configure
}

oe_cargo_build:append() {
    bbnote "${CARGO}" build ${CARGO_BUILD_FLAGS} --workspace "$@"
    ${CARGO} build ${CARGO_BUILD_FLAGS} --workspace "$@"
}

cargo_do_install() {
    install -d "${D}${libdir}"
    install -m755 "${B}/target/${CARGO_TARGET_SUBDIR}/libsysworxx_io.so" "${D}${libdir}"
    install -m755 "${B}/target/${CARGO_TARGET_SUBDIR}/libsysworxx_io_js.so" "${D}${libdir}/libsysworxx_io_js.node"

    # FIXME: Installing Python extensions this way seems hacky. We need to switch
    #        to maturin in the future.
    install -d "${D}${PYTHON_SITEPACKAGES_DIR}"
    install -m755 "${B}/target/${CARGO_TARGET_SUBDIR}/libsysworxx_io_py.so" "${D}${PYTHON_SITEPACKAGES_DIR}/sysworxx_io_py.so"

    install -d "${D}${bindir}"
    install -m755 "${B}/target/${CARGO_TARGET_SUBDIR}/iodaemon" "${D}${bindir}"
    install -m755 "${B}/target/${CARGO_TARGET_SUBDIR}/codesys-connector" "${D}${bindir}"

    install -d ${D}${includedir}
    install -m 0644 ${S}/include/sysworxx_io.h ${D}${includedir}

    install -d ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/systemd/iodaemon.service ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/Bindings/Codesys/systemd/codesys-connector.service ${D}${systemd_system_unitdir}
    install -m 0644 ${S}/Bindings/Codesys/systemd/codesys-generate-devdesc-xml.service ${D}${systemd_system_unitdir}
}

FILES:${PN} = " \
    ${libdir}/*.so \
    ${bindir}/iodaemon \
"

FILES:${PN}-js = " \
    ${libdir}/libsysworxx_io_js.node \
"

FILES:${PN}-py = " \
    ${PYTHON_SITEPACKAGES_DIR}/sysworxx_io_py.so \
"

FILES:${PN}-codesys-connector = " \
    ${bindir}/codesys-connector \
    ${systemd_system_unitdir}/codesys-connector.service \
    ${systemd_system_unitdir}/codesys-generate-devdesc-xml.service \
"

FILES:${PN}-dev = " \
    ${includedir}/*.h \
"

SYSTEMD_PACKAGES = " \
    ${PN} \
    ${PN}-codesys-connector \
"

SYSTEMD_SERVICE:${PN} = "iodaemon.service"
SYSTEMD_AUTO_ENABLE:${PN}-codesys-connector = "disable"
SYSTEMD_SERVICE:${PN}-codesys-connector += " \
    codesys-connector.service \
    codesys-generate-devdesc-xml.service \
"
