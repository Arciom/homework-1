package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("35ec65a26b60daee7626a358d6c0c259fbbdac49");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("Arciom", "homework-1")).commits();
    for(RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>()
            .build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }
}
