require 'rake_leiningen'

task :default do
  Rake::Task[:'app:check']
end

RakeLeiningen.define_installation_tasks(version: '2.9.3')

namespace :app do
  RakeLeiningen.define_check_tasks(fix: true)
  RakeLeiningen.define_build_task
end
