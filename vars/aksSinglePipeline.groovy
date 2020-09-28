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
            stage("Build Application"){
                agent{
                     docker{
                            image 'node:6-alpine'
                            args '-p 3000:3000'
                        }
                    }   

                steps{
                    sh 'RUN apk add --no-cache nodejs npm'
                    sh 'WORKDIR /app'
                    sh 'COPY . /app'
                    sh 'RUN npm install'
                }
            }


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
