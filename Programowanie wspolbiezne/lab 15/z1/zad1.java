
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class zad1 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		String path = System.getProperty("user.dir");
		File cat = new File(path);
		List<File> listOfCats = new ArrayList<File>();

		for (File file : cat.listFiles()) {
			System.out.println("Inside File: " + file.getName());
			listOfCats.add(file);
		}

		ForkJoinPool forkPool = new ForkJoinPool();
		forkPool.invoke(new Task(listOfCats, 0));

		System.out.println("MAX = ");
		forkPool = new ForkJoinPool();
	}
}

class Task extends RecursiveTask {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	final int size;
	List<File> cats;

	public Task(List<File> inputListOfCats, int s) {
		this.cats = inputListOfCats;
		this.size = s;
	}

	@Override
	protected Object compute() {
		if (cats.size() <= 2) {
			return sumSize(cats.get(cats.size() - 1));
		} else {
			List<Task> subtasks = CreateSubtasks();
			for (Task subtask : subtasks)
				subtask.fork();

			List<Task> output = new ArrayList<>();

			for (Task subtask : subtasks)
				output.add((Task) subtask.join());

			return sumSize(cats.remove(cats.size() - 1));
		}
	}

	private List<Task> CreateSubtasks() {
		List<Task> subtasks = new ArrayList<>();
		int length = cats.size();
		int halfLength = length / 2;

		List<File> firstHalfList = cats.subList(0, halfLength);
		Task firstSubtask = new Task(firstHalfList, size);
		System.out.println(this + " creatting subtask " + firstSubtask);
		subtasks.add(firstSubtask);

		List<File> secondtHalfList = cats.subList(halfLength, length);
		Task secondSubtask = new Task(secondtHalfList, size);
		System.out.println(this + "creatting subtask " + secondSubtask);
		subtasks.add(secondSubtask);

		return subtasks;
	}

	private long sumSize(File folder) {
		long length = 0;
		File[] files = folder.listFiles();
		if (cats == null || cats.size() == 0) {
			return length;
		}

		for (File file : files) {
			System.out.println("Inside File: " + file.getName());
			if (file.isFile()) {
				System.out.println("Size of file ( " + file.getName() + ") :" + file.length());
				length += file.length();
			} else {
				length += sumSize(file);
			}
		}
		return length;
	}
}