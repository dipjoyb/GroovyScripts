def call(Map pipelineArgs)
{
    def applicationName = pipelineArgs.projectName ?: "default"    


    pipeline{

        
    
        stages{

            stage("Build Application"){
            agent{
                docker{
                image 'alpine:latest'
                args '-p 3000:3000'
                }
            }           
            steps {
                 sh 'npm install'
                }
            }   

            stage("Pipeline Start"){
                environment{
                    APPLICATION_NAME = "${applicationName}"                  
                    CONTINUE_PIPELINE = true
                }
            agent{
                node{
                label "master"
                }
            }        


                steps{
                    aksPipeline()
                }
            }
        }
    }
}
