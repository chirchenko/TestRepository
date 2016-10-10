package polischukovik.services;

import java.io.FileNotFoundException;
import java.util.List;

import polischukovik.domain.QuestionRaw;
import polischukovik.mslibrary.Properties;

public interface QuestioRawnHandler {
	public List<QuestionRaw> parseSource() throws FileNotFoundException;
}
