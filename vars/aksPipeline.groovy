def call(){ 
    stage("Build Application"){
        steps {
            sh 'npm install'
        }
    }   

    stage("Run Unit Test"){
        echo"Running Unit Test"
    }    

    stage 'Docker build'
       docker.build('byjenkins')
    
    stage 'Docker push'
       docker.withRegistry('https://416764473985.dkr.ecr.us-east-1.amazonaws.com', 'ecr:us-east-1:bttrm-backend-user') {
       docker.image('byjenkins').push('latest')
    }
}
