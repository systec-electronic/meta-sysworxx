PACKAGE_ARCH = "${MACHINE_ARCH}"
SUMMARY = "Package groups for SYSTEC sysWORXX modules."
PR = "r1"

inherit packagegroup

PACKAGES = "\
    packagegroup-sysworxx \
    packagegroup-sysworxx-init \
    packagegroup-sysworxx-base \
    packagegroup-sysworxx-benchmark \
    packagegroup-sysworxx-extended \
    packagegroup-sysworxx-debug \
    packagegroup-sysworxx-develop \
    packagegroup-sysworxx-graphical \
    packagegroup-sysworxx-wifi-bt \
"

RDEPENDS:packagegroup-sysworxx = "\
    packagegroup-sysworxx-init \
    packagegroup-sysworxx-base \
    packagegroup-sysworxx-benchmark \
    packagegroup-sysworxx-extended \
    packagegroup-sysworxx-debug \
    packagegroup-sysworxx-develop \
    packagegroup-sysworxx-graphical \
    packagegroup-sysworxx-wifi-bt \
"

RDEPENDS:packagegroup-sysworxx-init = "\
    ${VIRTUAL-RUNTIME_initscripts} \
    ${VIRTUAL-RUNTIME_init_manager} \
    ${VIRTUAL-RUNTIME_login_manager} \
    ${VIRTUAL-RUNTIME_syslog} \
"

RDEPENDS:packagegroup-sysworxx-base = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'rauc', 'rauc', '', d)} \
    adc-setup \
    attr \
    bash \
    bzip2 \
    ca-certificates \
    can-setup \
    coreutils \
    cpio \
    cpufrequtils \
    curl \
    di-setup \
    dosfstools \
    dtbo-setup \
    e2fsprogs \
    e2fsprogs-mke2fs \
    e2fsprogs-resize2fs \
    file \
    findutils \
    gawk \
    grep \
    gzip \
    iperf3 \
    iproute2 \
    kernel-modules \
    less \
    libgpiod \
    libgpiod-tools \
    makedevs \
    mc \
    mc-helpers \
    mc-helpers-perl \
    nano \
    ncurses \
    net-tools \
    networkmanager \
    networkmanager-nmcli \
    openssh-sftp \
    openssh-sftp-server \
    parted \
    phy-lan8830t-setup \
    procps \
    psmisc \
    python3 \
    python3-as-python \
    python3-modules \
    python3-pip \
    rng-tools \
    rs485-setup \
    sed \
    sudo \
    systec-version \
    sysworxx-io \
    sysworxx-io-codesys-connector \
    sysworxx-io-js \
    sysworxx-io-py \
    tar \
    time \
    tzdata \
    udev-bootsource \
    usbutils \
    util-linux \
    util-linux-fstrim \
    vendor-setup \
"

RDEPENDS:packagegroup-sysworxx-benchmark = "\
    cpuburn-arm \
    dhrystone \
    memtester \
    tinymembench \
    whetstone \
"

RDEPENDS:packagegroup-sysworxx-extended = "\
    bash-completion \
    docker-moby \
    docker-compose \
    htop \
    mosquitto \
    mosquitto-clients \
    node-red \
    nodejs \
    nodejs-npm \
    openssl \
    vim \
"

RDEPENDS:packagegroup-sysworxx-graphical = "\
    kms++ \
    fb-test \
    fbv \
"

# TODO: we should probably not install the full vim experience, since this
#       introduces gtk dependencies

RDEPENDS:packagegroup-sysworxx-debug = "\
    can-utils \
    ethtool \
    evtest \
    i2c-tools \
    lsof \
    minicom \
    phytool \
    strace \
    tcpdump \
    mmc-utils \
"

RDEPENDS:packagegroup-sysworxx-develop = "\
    sysworxx-io-dev \
"

RDEPENDS:packagegroup-sysworxx-wifi-bt = "\
    bluez5 \
    iw \
    packagegroup-tools-bluetooth \
    wpa-supplicant \
    linux-firmware-summit-lwb5plus-sdio-sa \
"
