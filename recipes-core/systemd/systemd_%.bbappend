do_install:append:sysworxx() {
    sed -i \
        's/#RuntimeWatchdogSec=off/RuntimeWatchdogSec=30s/' \
        ${D}${sysconfdir}/systemd/system.conf
    sed -i \
        's/#Storage=auto/Storage=persistent/' \
        ${D}${sysconfdir}/systemd/journald.conf
}

FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "\
    file://systemctl.reboot.sh \
"

# turning of embedded machines in the field may be dangerous
do_install:append() {
    install -m 755 ${WORKDIR}/systemctl.reboot.sh ${D}${base_sbindir}
    rm ${D}${base_sbindir}/shutdown
    rm ${D}${base_sbindir}/halt
    rm ${D}${base_sbindir}/poweroff
    ln -s ${base_sbindir}/systemctl.reboot.sh ${D}${base_sbindir}/shutdown
    ln -s ${base_sbindir}/systemctl.reboot.sh ${D}${base_sbindir}/halt
    ln -s ${base_sbindir}/systemctl.reboot.sh ${D}${base_sbindir}/poweroff
}

FILES:${PN} += "${base_sbindir}/systemctl.reboot.sh"
ALTERNATIVE_TARGET[halt] = "${base_sbindir}/systemctl.reboot.sh"
ALTERNATIVE_TARGET[shutdown] = "${base_sbindir}/systemctl.reboot.sh"
ALTERNATIVE_TARGET[poweroff] = "${base_sbindir}/systemctl.reboot.sh"
