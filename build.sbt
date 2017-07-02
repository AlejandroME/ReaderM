scalaVersion := "2.12.2"
name := "ReaderM"
organization := "io.github.alejandrome"
version := "1.0"

val circeVersion = "0.8.0"
val doobieVersion = "0.4.1"
val akkaHttpVersion = "10.0.9"

libraryDependencies ++= Seq(
  "org.typelevel"             %%  "cats"                              % "0.9.0",
  "org.scalatest"             %%  "scalatest"                         % "3.0.3"                   % "test",
  "com.typesafe"              %   "config"                            % "1.3.1",
  "org.tpolecat"              %%  "doobie-core-cats"                  % doobieVersion,
  "org.tpolecat"              %%  "doobie-h2-cats"                    % doobieVersion,
  "org.tpolecat"              %%  "doobie-hikari-cats"                % doobieVersion,
  "org.tpolecat"              %%  "doobie-postgres-cats"              % doobieVersion,
  "org.tpolecat"              %%  "doobie-scalatest-cats"             % doobieVersion,
  "io.circe"                  %%  "circe-core"                        % circeVersion,
  "io.circe"                  %%  "circe-generic"                     % circeVersion,
  "io.circe"                  %%  "circe-parser"                      % circeVersion,
  "de.heikoseeberger"         %%  "akka-http-circe"                   % "1.17.0",
  "com.typesafe.akka"         %%  "akka-http"                         % akkaHttpVersion,
  "com.typesafe.akka"         %%  "akka-http-testkit"                 % akkaHttpVersion           % "test"
)

scalacOptions ++= Seq(
  "-encoding", "UTF-8",
  "-feature",
  "-deprecation",
  "-language:existentials",
  "-language:higherKinds",
  "-language:implicitConversions",
  "-language:experimental.macros",
  "-unchecked",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-value-discard"
)