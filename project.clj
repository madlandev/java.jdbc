;; NOTE: This project.clj file exists purely to make it easier to
;; develop and test java.jdbc locally. The pom.xml file is the
;; "system of record" as far as the project version is concerned.

(defproject org.clojure/java.jdbc "0.7.3-MADLAN"
  :description "A low-level Clojure wrapper for JDBC-based access to databases."
  :parent [org.clojure/pom.contrib "0.1.2"]
  :url "https://github.com/clojure/java.jdbc"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src/main/clojure"]
  :test-paths ["src/test/clojure"]
  :dependencies [[org.clojure/clojure "1.9.0"]
                 ;; These are just the versions most recently test against
                 ;; for your own projects, use whatever version is most
                 ;; appropriate for you. Again, note that this project.clj
                 ;; file exists for convenience -- the pom.xml file is the
                 ;; "system of record" as far as dependencies go!
                 ;; Note: 1.12.1.1 is used in pom.xml for Java 6/7 compat
                 [org.apache.derby/derby "10.13.1.1"]
                 [org.hsqldb/hsqldb "2.3.4"]
                 ;; Note: 1.4.191 is used in pom.xml for Java 6 compat
                 [com.h2database/h2 "1.4.193"]
                 [net.sourceforge.jtds/jtds "1.3.1"]
                 ;; Tests fail with 6.0.2 driver:
                 [mysql/mysql-connector-java "5.1.41"]
                 [org.postgresql/postgresql "9.4.1212.jre7"]
                 [com.impossibl.pgjdbc-ng/pgjdbc-ng "0.7.1"]
                 [org.xerial/sqlite-jdbc "3.16.1"]
                 ;; Assumes Java 8; there's a .jre7 version as well:
                 [com.microsoft.sqlserver/mssql-jdbc "6.2.2.jre8"]]
  :plugins [[s3-wagon-private "1.3.0"]]

  :profiles {:1.7 {:dependencies [[org.clojure/clojure "1.7.0"]]}
             :1.8 {:dependencies [[org.clojure/clojure "1.8.0"]]}
             :master {:dependencies [[org.clojure/clojure "1.10.0-master-SNAPSHOT"]]}
             :dev {:dependencies [[org.clojure/test.check "0.9.0"]
                                  [criterium "0.4.4"]]}
             :perf {:test-paths ["src/perf/clojure"]
                    :jvm-opts ^:replace ["-server"
                                         "-Xmx4096m"
                                         "-Dclojure.compiler.direct-linking=true"]}}
  :repositories {"sonatype-oss-public" "https://oss.sonatype.org/content/groups/public/"}
  ;; include dev profile with 1.9+ to pull in test.check
  :aliases {"test-all" ["with-profile"
                        "dev,test,master:dev,test:test,1.8:test,1.7"
                        "test"]
            "perf" ["with-profile" "default,dev,perf"]}
  :min-lein-version "2.0.0")
