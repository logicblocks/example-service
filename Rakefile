require 'rake_leiningen'

task :default => [:'app:check', :'test:all']

RakeLeiningen.define_installation_tasks(version: '2.9.3')

namespace :app do
  RakeLeiningen.define_check_tasks(fix: true)
  RakeLeiningen.define_build_task
  RakeLeiningen.define_start_task(profile: :server)
end

namespace :test do
  RakeLeiningen.define_test_task(
      name: :component,
      type: 'component',
      profile: 'component')

  desc 'Run all tests'
  task :all => ['test:component']
end
