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
    packagegroup-sysworxx-systec \
    packagegroup-sysworxx-networking \
"

RDEPENDS:packagegroup-sysworxx = "\
    packagegroup-sysworxx-init \
    packagegroup-sysworxx-base \
    packagegroup-sysworxx-benchmark \
    packagegroup-sysworxx-extended \
    packagegroup-sysworxx-debug \
    packagegroup-sysworxx-develop \
    packagegroup-sysworxx-graphical \
    packagegroup-sysworxx-systec \
    packagegroup-sysworxx-networking \
"

RDEPENDS:packagegroup-sysworxx-init = "\
    ${VIRTUAL-RUNTIME_initscripts} \
    ${VIRTUAL-RUNTIME_init_manager} \
    ${VIRTUAL-RUNTIME_login_manager} \
    ${VIRTUAL-RUNTIME_syslog} \
"

RDEPENDS:packagegroup-sysworxx-base = "\
    ${@bb.utils.contains('DISTRO_FEATURES', 'rauc', 'rauc', '', d)} \
    attr \
    bash \
    bash-completion \
    bzip2 \
    ca-certificates \
    coreutils \
    cpio \
    cpufrequtils \
    curl \
    dosfstools \
    e2fsprogs \
    e2fsprogs-mke2fs \
    e2fsprogs-resize2fs \
    file \
    findutils \
    gawk \
    grep \
    gzip \
    kernel-modules \
    less \
    libgpiod \
    libgpiod-tools \
    makedevs \
    ncurses \
    parted \
    procps \
    psmisc \
    rng-tools \
    sed \
    sudo \
    tar \
    time \
    tmux \
    tzdata \
    usbutils \
    util-linux \
    util-linux-fstrim \
"

RDEPENDS:packagegroup-sysworxx-systec = "\
    adc-setup \
    can-setup \
    di-setup \
    dtbo-setup \
    phy-lan8830t-setup \
    rs485-setup \
    systec-version \
    sysworxx-io \
    sysworxx-io-codesys-connector \
    sysworxx-io-js \
    sysworxx-io-py \
    udev-bootsource \
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
    docker-compose \
    docker-moby \
    htop \
    mc \
    mc-helpers \
    mc-helpers-perl \
    mosquitto \
    mosquitto-clients \
    nano \
    node-red \
    nodejs \
    nodejs-npm \
    openssl \
    python3 \
    python3-as-python \
    python3-modules \
    python3-pip \
    vim \
"

RDEPENDS:packagegroup-sysworxx-graphical = "\
    fb-test \
    fbv \
"

RDEPENDS:packagegroup-sysworxx-debug = "\
    can-utils \
    ethtool \
    evtest \
    i2c-tools \
    lsof \
    minicom \
    mmc-utils \
    phytool \
    strace \
    tcpdump \
"

RDEPENDS:packagegroup-sysworxx-develop = "\
    sysworxx-io-dev \
"

RDEPENDS:packagegroup-sysworxx-networking = "\
    bluez5 \
    iperf3 \
    iproute2 \
    iw \
    linux-firmware-summit-lwb5plus-sdio-sa \
    net-tools \
    networkmanager \
    networkmanager-nmcli \
    openssh-sftp \
    openssh-sftp-server \
    packagegroup-tools-bluetooth \
    wpa-supplicant \
"
