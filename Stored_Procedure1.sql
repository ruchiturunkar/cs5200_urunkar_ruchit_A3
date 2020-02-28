-- reference to stackOverflow
-- https://stackoverflow.com/questions/6110565/sql-select-rows-with-only-a-certain-value-in-them
drop procedure if exists cs5200_A3.getUnansweredQuestions;
DELIMITER $$
create procedure cs5200_A3.getUnansweredQuestions()
Begin 
select qt.id, qt.text, t.text, max(t.IncorrectAnswer), qt.module from (
select id, text, questionId, count(correctAnswer) as IncorrectAnswer
from answer a
group by a.questionId
having max(correctAnswer) = 0 and min(correctAnswer) = 0) t join question qt
on t.questionId = qt.id
group by qt.module;
end $$

-- call cs5200_A3.getUnansweredQuestions;

