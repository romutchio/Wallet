name := "Wallet"

version := "0.1"

scalaVersion := "2.12.4"

scalacOptions += "-Ypartial-unification"

val http4sVersion = "0.20.20"

libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-core" % "2.0.0",
    "org.tpolecat" %% "doobie-core" % "0.8.8",
    "com.github.cb372" %% "scalacache-guava" % "0.28.0",
    "com.github.cb372" %% "scalacache-memcached" % "0.28.0",
    "org.postgresql" % "postgresql" % "42.2.2",
    "org.http4s" %% "http4s-blaze-server" % http4sVersion,
    "org.http4s" %% "http4s-blaze-client" % http4sVersion,
    "org.http4s" %% "http4s-core" % http4sVersion,
    "org.http4s" %% "http4s-server" % http4sVersion,
    "org.http4s" %% "http4s-dsl" % http4sVersion,
    "org.http4s" %% "http4s-argonaut" % http4sVersion,
    "org.http4s"                  %% "http4s-circe"               % http4sVersion,
    "io.circe"                    %% "circe-generic"              % "0.11.2",
    "org.tpolecat" %% "skunk-core" % "0.0.3",
    "eu.timepit" %% "refined" % "0.9.13",
    "eu.timepit" %% "refined-cats" % "0.9.13",
    "is.cir" %% "ciris" % "1.0.4",
    "is.cir" %% "ciris-refined" % "1.0.4",
    "io.chrisdavenport" %% "log4cats-core"    % "1.0.1",  // Only if you want to Support Any Backend
    "io.chrisdavenport" %% "log4cats-slf4j"   % "1.0.1",  // Direct Slf4j Support - Recommended
)