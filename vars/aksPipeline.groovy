def call(){   
    stage("Run Unit Test"){
        def RESPONSE = checkUnitTestExists()
        if("${RESPONSE}"=="A"){
            def CONTINUE_ON_RUN_FAIL = runUnitTest()
            if("${CONTINUE_ON_RUN_FAIL}"==false){
                echo "Aborting Pipeline..."
                currentBuild.result = 'ABORTED'
            }
        }
        else if("${RESPONSE}"=="N"){
            echo "Aborting Pipeline..."
            currentBuild.result = 'ABORTED'
        }
    }
    stage("Docker Build"){   

                    echo "[INFO]: Docker Build"

                    script {
                        sh 'docker build -t nodejs-jenkins-test:latest .'
			sh 'aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 416764473985.dkr.ecr.us-east-1.amazonaws.com'
			sh 'docker tag nodejs-jenkins-test:latest 416764473985.dkr.ecr.us-east-1.amazonaws.com/byjenkins:latest'
			sh 'docker push 416764473985.dkr.ecr.us-east-1.amazonaws.com/byjenkins:latest'

                    }                        
    }
}
