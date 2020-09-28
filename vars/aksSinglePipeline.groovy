def call(Map pipelineArgs)
{
    def applicationName = pipelineArgs.projectName ?: "default"    


    pipeline{

       agent any 

        stages{  
           
            stage("Pipeline Start"){
                environment{
                    APPLICATION_NAME = "${applicationName}"                  
                    CONTINUE_PIPELINE = true
                }

                steps{
                    aksPipeline()
                }
            }
        }
    }
}
