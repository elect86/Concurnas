//version = '1.13.1'

import com.inet.gradle.setup.SetupBuilder
import com.inet.gradle.setup.msi.Msi
import groovy.lang.Closure
import java.lang.Double

val buildVersionName: Closure<Any> by ext

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        apply(from = "https://raw.githubusercontent.com/i-net-software/SetupBuilder/master/scripts/SetupBuilderVersion.gradle")
        val setupBuilderVersion = project.extensions.extraProperties["setupBuilderVersion"] as groovy.lang.Closure<*>
        classpath("gradle.plugin.de.inetsoftware:SetupBuilder:" + setupBuilderVersion())
        classpath("gradle.plugin.io.sdkman:gradle-sdkvendor-plugin:1.2.1")
    }
}

plugins {
    id("com.github.johnrengelman.shadow").version("5.1.0")
    `java-library`
    antlr
    java
    eclipse
}

apply(plugin = "io.sdkman.vendors")
apply(plugin = "de.inetsoftware.setupbuilder")

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

assert(JavaVersion.current().isJava9Compatible) { "Concurnas must be built on Java 9+" }

repositories {
    jcenter()
    mavenCentral()
    maven(url = "https://jitpack.io")
}

val libsForRuntime by configurations.creating

dependencies {
    api("com.google.jimfs:jimfs:1.1")
    testImplementation("junit:junit:4.12")
    libsForRuntime("org.antlr:antlr4:4.7.2")
    libsForRuntime("org.jline:jline:3.13.3")
    libsForRuntime("org.fusesource.jansi:jansi:1.18")
    api("org.jline:jline:3.13.3")
    api("org.fusesource.jansi:jansi:1.18")
    antlr("org.antlr:antlr4:4.7.2")
}

sourceSets {
    main {
        java {
            srcDir("src/asm")
            srcDir("src/compiler")
            srcDir("src/build")
            srcDir("src/runtimeCache")
            srcDir("src/jocl")
            srcDir("src/runtime")
            srcDir("src/tools")
            srcDir("src/bootstrap")
            srcDir("src_derived")
        }
    }

    this.test {
        java {
            srcDir("tests")
        }
    }
}

tasks.withType(JavaCompile::class.java) {
    options.isWarnings = false
}


/////////////////////////////////////// Concurnas specific ///////////////////////////////////////

tasks {
    val runVersionSplicer by registering(type = JavaExec::class) {
        classpath = sourceSets["main"].runtimeClasspath
        main = "com.concurnas.build.VersionSplicer"
    }

    val runCacheCreator by registering(type = JavaExec::class) {
        classpath = sourceSets["main"].runtimeClasspath
        main = "com.concurnas.runtimeCache.RuntimeCacheCreator"
    }

    val runLibCompile by registering(type = JavaExec::class) {
        classpath = sourceSets["main"].runtimeClasspath
        main = "com.concurnas.build.LibCompilation"
        jvmArgs = getJVMArgs()
        description = "Compiles all .conc libraries"
        group = "Concurnas"

        args = listOf(project.buildDir.toString() + "/classes/java/main")
    }

    compileJava { finalizedBy(runVersionSplicer) }
    runVersionSplicer { finalizedBy(runCacheCreator) }
    runCacheCreator { finalizedBy(runLibCompile) }

//    test { enabled = false }

//    replace("test", JavaExec::class)

//    getByName("test", JavaExec::class) {
//        description = "Runs all junit tests"
//        group = "Verification"
//        classpath = sourceSets["main"].runtimeClasspath + sourceSets["test"].runtimeClasspath
//        main = "com.concurnas.compiler.TestRunner"
//
//        jvmArgs = getJVMArgs()
//    }

    val runTestsCompile by registering(type = JavaExec::class) {
        classpath = sourceSets["main"].runtimeClasspath
        main = "com.concurnas.concc.Concc"
        jvmArgs = getJVMArgs()
        description = "Compiles all .conc test helpers"
        group = "Concurnas"

        args = listOf("-d " + project.buildDir.toString() + "/classes/java/test" + " ./tests[com/concurnas/tests/helpers]")
    }

//    test {
//        dependsOn(compileTestJava)
//        dependsOn(runTestsCompile)
//    }

//////////////////////////////////////////// ANTLR ////////////////////////////////////////////
    generateGrammarSource {
        maxHeapSize = "64m"

        outputDirectory = file("src_derived")

        arguments = listOf("-no-listener", "-visitor")
    }

    clean {
        doLast {
            file("src_derived").delete()
        }
    }
}

///////////////////////// function to obtain jvm args -Xbootclasspath /////////////////////////
fun readVersion() = file("book/version.tex").readLines()[0].split(" ")[0]

///////////////////////// function to obtain jvm args -Xbootclasspath /////////////////////////

fun getJVMArgs(): ArrayList<String> {
    val modules = Double.parseDouble(System.getProperty("java.specification.version")) >= 1.9

    val argumentList = arrayListOf(when {
        modules -> "--patch-module=java.base=./installed/java.base.jar --patch-module=java.compiler=./installed/java.compiler.jar --patch-module=java.datatransfer=./installed/java.datatransfer.jar --patch-module=java.desktop=./installed/java.desktop.jar --patch-module=java.instrument=./installed/java.instrument.jar --patch-module=java.logging=./installed/java.logging.jar --patch-module=java.management=./installed/java.management.jar --patch-module=java.management.rmi=./installed/java.management.rmi.jar --patch-module=java.naming=./installed/java.naming.jar --patch-module=java.net.http=./installed/java.net.http.jar --patch-module=java.prefs=./installed/java.prefs.jar --patch-module=java.rmi=./installed/java.rmi.jar --patch-module=java.scripting=./installed/java.scripting.jar --patch-module=java.security.jgss=./installed/java.security.jgss.jar --patch-module=java.security.sasl=./installed/java.security.sasl.jar --patch-module=java.smartcardio=./installed/java.smartcardio.jar --patch-module=java.sql=./installed/java.sql.jar --patch-module=java.sql.rowset=./installed/java.sql.rowset.jar --patch-module=java.transaction.xa=./installed/java.transaction.xa.jar --patch-module=java.xml.crypto=./installed/java.xml.crypto.jar --patch-module=java.xml=./installed/java.xml.jar --patch-module=jdk.accessibility=./installed/jdk.accessibility.jar --patch-module=jdk.attach=./installed/jdk.attach.jar --patch-module=jdk.charsets=./installed/jdk.charsets.jar --patch-module=jdk.compiler=./installed/jdk.compiler.jar --patch-module=jdk.crypto.cryptoki=./installed/jdk.crypto.cryptoki.jar --patch-module=jdk.crypto.ec=./installed/jdk.crypto.ec.jar --patch-module=jdk.crypto.mscapi=./installed/jdk.crypto.mscapi.jar --patch-module=jdk.dynalink=./installed/jdk.dynalink.jar --patch-module=jdk.editpad=./installed/jdk.editpad.jar --patch-module=jdk.httpserver=./installed/jdk.httpserver.jar --patch-module=jdk.internal.ed=./installed/jdk.internal.ed.jar --patch-module=jdk.internal.jvmstat=./installed/jdk.internal.jvmstat.jar --patch-module=jdk.internal.le=./installed/jdk.internal.le.jar --patch-module=jdk.internal.opt=./installed/jdk.internal.opt.jar --patch-module=jdk.jartool=./installed/jdk.jartool.jar --patch-module=jdk.javadoc=./installed/jdk.javadoc.jar --patch-module=jdk.jconsole=./installed/jdk.jconsole.jar --patch-module=jdk.jdeps=./installed/jdk.jdeps.jar --patch-module=jdk.jdi=./installed/jdk.jdi.jar --patch-module=jdk.jdwp.agent=./installed/jdk.jdwp.agent.jar --patch-module=jdk.jfr=./installed/jdk.jfr.jar --patch-module=jdk.jlink=./installed/jdk.jlink.jar --patch-module=jdk.jshell=./installed/jdk.jshell.jar --patch-module=jdk.jsobject=./installed/jdk.jsobject.jar --patch-module=jdk.jstatd=./installed/jdk.jstatd.jar --patch-module=jdk.localedata=./installed/jdk.localedata.jar --patch-module=jdk.management.agent=./installed/jdk.management.agent.jar --patch-module=jdk.management=./installed/jdk.management.jar --patch-module=jdk.management.jfr=./installed/jdk.management.jfr.jar --patch-module=jdk.naming.dns=./installed/jdk.naming.dns.jar --patch-module=jdk.naming.rmi=./installed/jdk.naming.rmi.jar --patch-module=jdk.net=./installed/jdk.net.jar --patch-module=jdk.scripting.nashorn=./installed/jdk.scripting.nashorn.jar --patch-module=jdk.sctp=./installed/jdk.sctp.jar --patch-module=jdk.security.auth=./installed/jdk.security.auth.jar --patch-module=jdk.security.jgss=./installed/jdk.security.jgss.jar --patch-module=jdk.unsupported.desktop=./installed/jdk.unsupported.desktop.jar --patch-module=jdk.unsupported=./installed/jdk.unsupported.jar --patch-module=jdk.xml.dom=./installed/jdk.xml.dom.jar --patch-module=jdk.zipfs=./installed/jdk.zipfs.jar --add-exports=java.base/com.concurnas.bootstrap.runtime.cps=ALL-UNNAMED,java.base,java.compiler,java.datatransfer,java.desktop,java.instrument,java.logging,java.management,java.management.rmi,java.naming,java.net.http,java.prefs,java.rmi,java.scripting,java.security.jgss,java.security.sasl,java.smartcardio,java.sql,java.sql.rowset,java.transaction.xa,java.xml.crypto,java.xml,jdk.accessibility,jdk.attach,jdk.charsets,jdk.compiler,jdk.crypto.cryptoki,jdk.crypto.ec,jdk.crypto.mscapi,jdk.dynalink,jdk.editpad,jdk.httpserver,jdk.internal.ed,jdk.internal.jvmstat,jdk.internal.le,jdk.internal.opt,jdk.jartool,jdk.javadoc,jdk.jconsole,jdk.jdeps,jdk.jdi,jdk.jdwp.agent,jdk.jfr,jdk.jlink,jdk.jshell,jdk.jsobject,jdk.jstatd,jdk.localedata,jdk.management.agent,jdk.management,jdk.management.jfr,jdk.naming.dns,jdk.naming.rmi,jdk.net,jdk.scripting.nashorn,jdk.sctp,jdk.security.auth,jdk.security.jgss,jdk.unsupported.desktop,jdk.unsupported,jdk.xml.dom,jdk.zipfs --add-exports=java.base/com.concurnas.bootstrap.lang.offheap=java.instrument --add-exports=java.base/com.concurnas.bootstrap=ALL-UNNAMED --add-exports=java.base/com.concurnas.bootstrap.lang=ALL-UNNAMED --add-exports=java.base/com.concurnas.bootstrap.lang.offheap=ALL-UNNAMED --add-exports=java.base/com.concurnas.bootstrap.lang.util=ALL-UNNAMED --add-exports=java.base/com.concurnas.bootstrap.runtime=ALL-UNNAMED --add-exports=java.base/com.concurnas.bootstrap.runtime.ref=ALL-UNNAMED --add-exports=java.base/com.concurnas.bootstrap.runtime.transactions=ALL-UNNAMED --add-exports=java.base/jdk.internal.ref=ALL-UNNAMED --add-opens=java.base/java.nio=ALL-UNNAMED --add-opens=java.base/com.concurnas.bootstrap.lang=ALL-UNNAMED"
        else -> "-Xbootclasspath:./installed/rt.jar;./installed/resources.jar;./installed/plugin.jar;./installed/management-agent.jar;./installed/jsse.jar;./installed/jfxswt.jar;./installed/jfr.jar;./installed/jce.jar;./installed/javaws.jar;./installed/deploy.jar;./installed/charsets.jar;./installed/ext/access-bridge-64.jar;./installed/ext/cldrdata.jar;./installed/ext/dnsns.jar;./installed/ext/jaccess.jar;./installed/ext/jfxrt.jar;./installed/ext/localedata.jar;./installed/ext/nashorn.jar;./installed/ext/sunec.jar;./installed/ext/sunjce_provider.jar;./installed/ext/sunmscapi.jar;./installed/ext/sunpkcs11.jar;./installed/ext/zipfs.jar;./installed/security/local_policy.jar;./installed/security/US_export_policy.jar;./installed/conccore.jar"
    })

    argumentList += "-Djava.io.tmpdir=./tmp"
    return argumentList
}

//////////////////////////////////////////// jar ////////////////////////////////////////////

tasks {
    jar {
        archiveBaseName.set("Concurnas")
        archiveVersion.set(readVersion())
        exclude("com/concurnas/build/**")
        from("LICENSE")
        from("src/tools") {
            include("com/concurnas/repl/*.nanorc")
        }
        from(sourceSets["main"].output) {
            include("com/concurnas/lang/**")
        }
        from(sourceSets["main"].output) {
            include("com/concurnas/runtime/**")
        }
        from(sourceSets["main"].output) {
            include("org/jocl/**")
        }
        from("src/jocl") {
            include("lib/**")
        }
        from(libsForRuntime.map { if (it.isDirectory) it else zipTree(it) })
    }

    val copyJarsToRelease by registering(type = Copy::class) {
        from("$buildDir/libs")
        into("release/lib")
    }
    val copyLic by registering(type = Copy::class) {
        from("LICENSE")
        into("release")
    }

    val copyBook by registering(type = Copy::class) {
        from("book/conc-doc.pdf")
        into("release/docs")
    }

    val makeReleaseZip by registering(type = Zip::class) {
        archiveBaseName.set("Concurnas")
        archiveVersion.set(readVersion())

        from("release") {
            exclude("bin/**")
        }
        from("release") {
            include("bin/**")
            fileMode = 755
        }

        into("concurnas")
    }

    val copyDist by registering(type = Copy::class) {
        from("$buildDir/distributions")
        into(".")
    }

    jar { finalizedBy(copyJarsToRelease) }
    copyJarsToRelease { finalizedBy(copyLic) }
    copyLic { finalizedBy(copyBook) }
    copyBook { finalizedBy(makeReleaseZip) }
    makeReleaseZip { finalizedBy(copyDist) }
}

configure<SetupBuilder> {
    vendor = "Concurnas Ltd"
    copyright = "Copyright © 2020 Concurnas Ltd."
    description = "Concurnas ${readVersion()} installer"
    application = "Concurnas"
    appIdentifier = "Concurnas"
    defaultResourceLanguage = "en"
    version = readVersion()
    licenseFile("LICENSE")
    from("release") {
        exclude("bin/**")
    }
    from("release") {
        include("bin/**")
        fileMode = 755
    }
}

val msi by tasks.named("msi", Msi::class) {
    setLanguages("en-US")
    setWxsTemplate("./build_resources/msitemplate.wxs")
}

val zipmsi by tasks.registering(type = Zip::class) {
    from("$buildDir/distributions") {
        include("**.msi")
    }

    archiveFileName.set(provider {
        fileTree("$buildDir/distributions").filter { it.isFile && it.toString().endsWith("msi") }.files.elementAt(0).name + ".zip"
    })
}

msi.finalizedBy(zipmsi)
zipmsi { finalizedBy(tasks["copyDist"]) }

//tasks.forEach { println(it::class) }
//tasks.named("SdkmanExtension", io.sdkman.vendors.model.SdkmanExtension::class) {
//    api = "https://vendors.sdkman.io/"
//    consumerKey = System.getenv("SDKVENDOR_KEY")
//    consumerToken = System.getenv("SDKVENDOR_TOKEN")
//    candidate = "concurnas"
//    version = readVersion()
//    url = "https://github.com/Concurnas/Concurnas/releases/download/${readVersion()}/Concurnas-${readVersion()}.zip"
//    hashtag = "#concurnas"
//}


