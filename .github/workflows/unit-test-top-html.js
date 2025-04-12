const fs = require('node:fs');
const path = require('node:path');

export const createTopHtml = () => {
  const testReportDir = 'build/build-outputs/unit-test-report';
  const outputFile = path.join(testReportDir, 'index.html');

  function generateHTML(files) {
    // ファイルパスをディレクトリ構造に変換
    const structure = {};
    for (const file of files) {
      const relativePath = path.relative(testReportDir, file);
      const dirPath = path.dirname(relativePath);
      const parts = dirPath.split(path.sep);
      let current = structure;

      for (const [index, part] of parts.entries()) {
        if (index === parts.length - 1) {
          current[part] = dirPath;
        } else {
          if (!current[part]) {
            current[part] = {};
          }
          current = current[part];
        }
      }
    }

    // ディレクトリ構造をHTMLに変換
    function generateList(items) {
      if (typeof items === 'string') {
        return `<li><a href="${items}/index.html">${path.basename(items)}</a></li>`;
      }

      return Object.entries(items).map(([key, value]) => {
        if (typeof value === 'string') {
          return `<li><a href="${value}/index.html">${path.basename(value)}</a></li>`;
        }
        return `<li>${key}<ul>${generateList(value)}</ul></li>`;
      }).join('\n');
    }

    const html = `<!DOCTYPE html>
  <html>
  <head>
      <title>Unit Test Reports Summary</title>
      <style>
          body {
              font-family: Arial, sans-serif;
              margin: 20px;
              line-height: 1.6;
          }
          h1 {
              color: #333;
          }
          a {
              color: #0066cc;
              text-decoration: none;
          }
          a:hover {
              text-decoration: underline;
          }
      </style>
  </head>
  <body>
      <h1>Unit Test Reports</h1>
      <ul>
          ${generateList(structure)}
      </ul>
  </body>
  </html>`;

    return html;
  }

  function findHTMLFiles(dir) {
    let files = [];
    const items = fs.readdirSync(dir);

    for (const item of items) {
      const fullPath = path.join(dir, item);
      const stat = fs.statSync(fullPath);

      if (stat.isDirectory()) {
        files = files.concat(findHTMLFiles(fullPath));
      } else if (item === 'index.html' && fullPath !== outputFile) {
        files.push(fullPath);
      }
    }

    return files;
  }

  // Main execution
  try {
    const htmlFiles = findHTMLFiles(testReportDir);
    const htmlContent = generateHTML(htmlFiles);
    fs.writeFileSync(outputFile, htmlContent);
    console.log(`Successfully generated ${outputFile}`);
  } catch (error) {
    console.error('Error generating test reports list:', error);
  }
}
