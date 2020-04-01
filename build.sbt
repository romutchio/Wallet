name := "Wallet"

version := "0.1"

scalaVersion := "2.12.4"

scalacOptions += "-Ypartial-unification"

libraryDependencies ++= Seq(
  "org.typelevel"    %% "cats-core"            % "2.0.0",
  "org.tpolecat"     %% "doobie-core"          % "0.8.8",
  "com.github.cb372" %% "scalacache-guava"     % "0.28.0",
  "com.github.cb372" %% "scalacache-memcached" % "0.28.0",
  "org.postgresql"   % "postgresql"           % "42.2.2"
)