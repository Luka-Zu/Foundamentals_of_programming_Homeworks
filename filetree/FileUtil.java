package filetree;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
public class FileUtil {
	public static File toFileRepresentation(Path path) throws IOException {
		if(Files.isDirectory(path)){
			List<Path> listOfPathsToGetListOfFiles = Files.list(path).collect(Collectors.toList());
			List<File> listOfFiles = new LinkedList<>();
			for(Path path1 : listOfPathsToGetListOfFiles){
				listOfFiles.add(toFileRepresentation(path1));
			}
			return new Directory(path,listOfFiles);

		}else{
			return new RegularFile(path);
		}
	}
}
