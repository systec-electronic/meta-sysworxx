do_install:append:sysworxx() {
    sed -i \
        's/#RuntimeWatchdogSec=off/RuntimeWatchdogSec=30s/' \
        ${D}${sysconfdir}/systemd/system.conf
    sed -i \
        's/#Storage=auto/Storage=persistent/' \
        ${D}${sysconfdir}/systemd/journald.conf
}
