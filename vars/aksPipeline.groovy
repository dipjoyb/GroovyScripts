def call(){   
    stage("Run Unit Test"){
        echo"Running Unit Test"
    }
    stage 'Checkout'
       git 'ssh://git@github.com:dipjoyb/testPipelineDipjoy.git'

    stage 'Docker build'
       docker.build('byjenkins')
    
    stage 'Docker push'
       docker.withRegistry('416764473985.dkr.ecr.us-east-1.amazonaws.com', 'ecr:us-east-1:bttrm-backend-user') {
       docker.image('byjenkins').push('latest')
    }
}
