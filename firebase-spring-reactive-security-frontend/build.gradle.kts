import com.moowork.gradle.node.yarn.YarnTask
import com.moowork.gradle.node.yarn.YarnInstallTask

plugins {
    id("base")
    id("com.moowork.node") version "1.3.1"
}

tasks.create<YarnInstallTask>("yarnInstall") {}

tasks.create<YarnTask>("buildFrontend") {
    args = listOf("build")
}

tasks.create<YarnTask>("testFrontend") {
    args = listOf("test")
}

tasks.getByName("buildFrontend").dependsOn("yarnInstall")
tasks.getByName("assemble").dependsOn("buildFrontend")

