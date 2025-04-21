export const updatePr = async ({
  github,
  core,
  context,
  prNumber,
  hasChanges,
  imageMarkdown,
  metaDataMarkdown,
}) => {
  try {
    // Create GitHub client
    const octokit = github;

    // Get PR details
    const { data: pr } = await octokit.rest.pulls.get({
      owner: context.repo.owner,
      repo: context.repo.repo,
      pull_number: prNumber
    });

    // Replace content between screenshots tags
    const newBody = pr.body.replace(
      /<!--\s*screenshots-start(\s+toggle)?\s*-->[\s\S]*?<!--\s*screenshots-end\s*-->/g,
      (_, hasToggle) => {
        const content = !hasChanges
          ? `No visual changes 👍\n\n${metaDataMarkdown}`
          : hasToggle
            ? `<details>\n<summary>Screenshots</summary>\n${imageMarkdown}\n</details>`
            : `${imageMarkdown}\n\n${metaDataMarkdown}`;
        return `<!--screenshots-start${hasToggle ? ' toggle' : ''}-->\n${content}\n<!--screenshots-end-->`;
      }
    )

    console.log(newBody)

    // Update PR body
    await octokit.rest.pulls.update({
      owner: context.repo.owner,
      repo: context.repo.repo,
      pull_number: prNumber,
      body: newBody
    });
  } catch (error) {
    core.setFailed(error.message);
  }
}
