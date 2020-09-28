def call(Map pipelineArgs)
{
    def applicationName = pipelineArgs.projectName ?: "default"    


    pipeline{

        agent{
            node{
                label "master"
            }
        }        
    
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
