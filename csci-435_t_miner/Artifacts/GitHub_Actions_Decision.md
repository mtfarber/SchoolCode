### Decision to Use Github Actions
In our project, we evaluted the differences between Github Actions and GitHub Apps and detemined that GitHub Actions would be better for our purposes. Our evaluation and decision is seen below:

      GitHub Apps:
      * Automate and improve workflows
      * Installed directly on organizations or personal accounts and can be given access to specific repositories
      * Take actions independent of a user, via the API
      * Must have permissions to use

      Github Actions: 
      * Ability to automate workflows which are triggered by events such as pull requests and branch merging
      * CI/CD functionality
      * Simple, commit a configuration file to enable an action
      * Free for any GitHub repository

We ultimately decided to use GitHub Actions for the following reasons:
* Can run directly in docker containers or VM (runner), doesn't need a server or any type of infrastructure
* Avoids the step of deploying code and serving up an app
* Easier than apps to create and store secrets, which make it eaiser to interact with third party services (does not store credentials)
* Easier automation based off triggers in your repository (pull requests), so it doesn't need to be continuously running in the background
* Easy creation of a workflow using a simple YAML file
