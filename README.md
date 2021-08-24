# SBT multi level project (with independent job-transformation build)

It would be nice to have one's cake and eat it.

## Motivation
1. Build project at top most hierarchy level.
2. Have DRY 'top' level references e.g. Dependencies, Versions, etc.
3. Build project only at job-transformation level (independently).

Building from the top is simple and easy, `dependsOn` works and all job-transformations compile depending... all the code in one place, its all nice and good.

However, if you really just want to focus on the job-transformation project, you're in and out - that would be terrific.

Think large project, with lots of job-transformation, could be an API, utility or micro services, whatever.
The ability to make cheap up-stream *project* changes is great, when checking out at the 'top' most project level. Any change can ripple down and fixes be made at any job-transformation that depends on the dependent changed module (up/down).

At the job-transformation level, it's probable that this would be deployed independently. When a new feature is needed, check out only what's needed at the job-transformation(s) level (possible with [git sparse checkout](https://git-scm.com/docs/git-read-tree#_sparse_checkout)). Implement, Build, Deploy and Done - but rely on existing pre-released 'stable' dependent modules.
 
## Building/Testing Strategy
1. Should be able to build at 'bleeding' edge for whole project and test this at the 'top' level.
2. Should be able to build at the individual job-transformation level with 'stable' pre-released dependencies.
3. For #1 and #2 these should make use of the same DRY references to dependencies (employ sbt build library `/project` dir).
4. Versions on job-transformation can differ, don't need to have whole project 'top to bottom' have a version change.
5. With more funky situations i.e. features or dependency versions (already in the wild), existing scm branching can be employed.

## Cheeky way round
* Symbolic link 'top' project settings with external script (sorry no script windows peeps)
* In job-transformation build reference project settings + any stable released library dependencies (needs `-Dindependent=true` arg to be passed to sbt)

Script #1 links and unlinks 'top' project files, as well as runs sbt by passing `-Dindependent=true` arg to sbt.

## Setup
1. Copy bash script in source `bash/top-project-linker.sh` somewhere.
2. alias script as sbt-tpl e.g. `alias sbt-tpl="../path/to/top-project-linker.sh"`.
3. In the job-transformation ensure project has common settings see `connection` + `transformation` build.sbt files.
4. If the job-transformation needs dependencies, append these to `libraryDependencies` see jobTransformation build.sbt file.

## Usage
#### Top (normal) build
1. Change to 'top' most project dir.
2. Use `sbt` as usual.

#### Independent transformation build 
1. change dir to a job-transformation.
2. run script via alias `sbt-tpl`.
3. do whatever you need via sbt (when you're done, exit #2 script will clean up).

## Gotchas
* `top-project-linker.sh` script expects Common.scala + Dependencies.scala, change these if your project is different and add if you need more files to be linked (hard coded, but good enough).
