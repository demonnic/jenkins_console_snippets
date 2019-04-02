import jenkins.model.Jenkins
import hudson.model.Job
 
MILLIS_IN_A_DAY = (60*60*24*1000)
SEVEN_DAYS = 7 * MILLIS_IN_A_DAY
CURRENT_EPOCH = new Date().getTime()
builds_deleted = 0
 
Jenkins.instance.getAllItems(Job.class).each { job ->
  for (build in job.builds) {
    milliseconds_ago = CURRENT_EPOCH - build.startTimeInMillis
    days_ago = Math.round(milliseconds_ago / MILLIS_IN_A_DAY * 100) / 100
    if (milliseconds_ago > SEVEN_DAYS) {
      println "Preparing to delete: ${build} which is ${days_ago} days old"
      builds_deleted++
      //build.delete()
    }
  }
}
println "Deleted ${builds_deleted} build(s)"
