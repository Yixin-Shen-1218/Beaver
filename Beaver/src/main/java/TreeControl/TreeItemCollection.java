package TreeControl;

import Main.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> Tree Item Collection that read the tree source file.
 * Tree Item Collection that read the tree source file to initialize the user's archive file.
 *
 * @author Yixin SHEN
 * @version 1.0
 * @date 19/01/2021-14:03
 * @since 1.0
 */
public class TreeItemCollection {
    /**
     * model list
     */
    private List<TreeItemObject> ModelsList = new ArrayList<>();
    /**
     * data set list
     */
    private List<TreeItemObject> DataSetsList = new ArrayList<>();
    /**
     * scenario list
     */
    private List<TreeItemObject> ScenariosList = new ArrayList<>();
    /**
     * record list
     */
    private List<TreeItemObject> RecordsList = new ArrayList<>();

    /**
     * TreeItemCollection
     * <p>
     * Constructor that read initialize the tree collection
     *
     * @throws IOException file I/O exception
     * @author Ruibin CHEN
     * @date 2021/3/14-20:44
     */
    public TreeItemCollection() throws IOException {
        String fileName = "TreeSource/" + Main.username + "Source.txt";
        File file = new File(fileName);
        if (file.exists()) {
            System.out.println(file.getAbsolutePath());
            ReadSource(fileName);
        } else {
            System.out.println("Read default file");
            ReadSource("TreeSource/DefaultSource.txt");
        }
    }

    /**
     * Read the user status source file.
     * <p> Read the source file, initialize the ArrayList according to the file.
     *
     * @param FilePath The user status source file.
     * @throws IOException IOException
     * @author Yixin SHEN
     * @date 24/01/2021-20:24
     */
    private void ReadSource(String FilePath) throws IOException {
        // Package
        InputStream in = new FileInputStream(FilePath);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            String name = "";
            boolean favor = false;
            String value = "";
            int type;

            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }

                if (line.contains("Name")) {
                    name = line.replace("Name: ", "");
                    continue;
                }

                if (line.contains("Value")) {
                    value = line.replace("Value: ", "");
                    continue;
                }

                if (line.contains("Favor")) {
                    favor = !line.replace("Favor: ", "").equals("false");
                    continue;
                }

                if (line.contains("Type")) {
                    type = Integer.parseInt(line.replace("Type: ", ""));
                    TreeItemObject treeItemObject = new TreeItemObject(type, favor, name, value);
                    if (type == 1) {
                        this.ModelsList.add(treeItemObject);
                    } else if (type == 2) {
                        this.DataSetsList.add(treeItemObject);
                    } else if (type == 3) {
                        this.ScenariosList.add(treeItemObject);
                    } else {
                        this.RecordsList.add(treeItemObject);
                    }
                }
            }
        }
    }

    /**
     * getModelsList
     * <p>
     * models list getter
     *
     * @return models list
     * @author Ruibin CHEN
     * @date 2021/3/14-20:47
     */
    public List<TreeItemObject> getModelsList() {
        return ModelsList;
    }

    /**
     * getDataSetsList
     * <p>
     * datasets list getter
     *
     * @return datasets list
     * @author Ruibin CHEN
     * @date 2021/3/14-20:48
     */
    public List<TreeItemObject> getDataSetsList() {
        return DataSetsList;
    }

    /**
     * getScenariosList
     * <p>
     * scenarios list getter
     *
     * @return scenarios list
     * @author Ruibin CHEN
     * @date 2021/3/14-20:49
     */
    public List<TreeItemObject> getScenariosList() {
        return ScenariosList;
    }

    /**
     * getRecordsList
     * <p>
     * records list getter
     *
     * @return records list
     * @author Ruibin CHEN
     * @date 2021/3/14-20:49
     */
    public List<TreeItemObject> getRecordsList() {
        return RecordsList;
    }

}
