// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.9.10" apply false
}

subprojects {
    configurations.configureEach {
        resolutionStrategy.cacheChangingModulesFor(0, TimeUnit.SECONDS)
    }

    pluginManager.withPlugin("maven-publish") {
        val sonatypeUserName: String by project
        val sonatypePassword: String by project

        val GROUP: String by project
        val ARTIFACT: String by project
        val VERSION: String by project

        val publishExtension = extensions.getByType<PublishingExtension>()
        publishExtension.repositories {
            mavenLocal()
            maven {
                val url = if (VERSION.endsWith("-SNAPSHOT")) {
                    "https://s01.oss.sonatype.org/content/repositories/snapshots/"
                } else {
                    "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
                }
                setUrl(url)
                credentials {
                    username = sonatypeUserName
                    password = sonatypePassword
                }
            }
        }
        publishExtension.publications.whenObjectAdded {
            check(this is MavenPublication) {
                "unexpected publication $this"
            }
            groupId = GROUP
            artifactId = ARTIFACT
            version = VERSION
            pom {
                url.set("https://github.com/porum/Logan")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://github.com/porum/Logan/blob/master/LICENSE")
                    }
                }
            }
        }
    }

    pluginManager.withPlugin("signing") {
        val signingExtension = extensions.getByType<SigningExtension>()
        signingExtension.sign(extensions.getByType<PublishingExtension>().publications)
    }
}