def call(Map pipelineArgs)
{
    def applicationName = pipelineArgs.projectName ?: "default"


    
    def dockerArtifactory = 416764473985.dkr.ecr.us-east-1.amazonaws.com/byjenkins:latest

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
                    DOCKER_ARTIFACTORY = "${dockerArtifactory}"                    
                    CONTINUE_PIPELINE = true
                }

                steps{
                    aksPipeline()
                }
            }
        }
    }
}
