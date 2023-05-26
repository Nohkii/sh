package org.zerock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.zerock.domain.PDSBoard;
import org.zerock.domain.PDSFile;
import org.zerock.domain.Profile;
import org.zerock.persistence.PDSBoardRepository;

import lombok.extern.java.Log;

@AutoConfigureMockMvc
@SpringBootTest
@Log
@Commit
public class PDSBoardTests {

	@Autowired
	PDSBoardRepository repo;
	
	@Test
	public void testInsertPDS() {
		PDSBoard pds = new PDSBoard();
		pds.setPname("Document");
		
		PDSFile file1 = new PDSFile();
		file1.setPdsfile("file1.doc");
		PDSFile file2 = new PDSFile();
		file2.setPdsfile("file2.doc");
		
		pds.setFiles(Arrays.asList(file1, file2));
		log.info("save");
		repo.save(pds);
	}
	
	@Test
	public void test() {
		Profile p1 = new Profile();
		p1.setFname("홍길동");
		p1.setFno(1L);
		
		Profile p2 = new Profile();
		p2.setFname("홍길동");
		p2.setFno(1L);
		
		System.out.println(p1 == p2);
		System.out.println(p1.equals(p2));
	}
	
	@Test
	public void test2() {
		System.out.println(2/1);
	}
	
	@Transactional
	@Test
	public void testUpdateFileName1() {
		Long fno = 1L;
		// 왜 L을 붙였지? Long이니까 붙였구나~ 이렇게 까먹었어도 다시 보면서 기억하면 된다~
		String newName = "updatedFile1.doc";
		int count = repo.updatePDSFile(fno, newName);
		log.info("update count:"+count);
	}
	@Transactional
	@Test
	public void testUpdateFileName2() {
		Long fno = 2L;
		String newName = "updatedFile2.doc";
		
		Optional<PDSBoard> result = repo.findById(fno);
		// fno2를 가지고 찾은 객체가 result에 담김

		// 데이터가 존재해야 돌아감
		result.ifPresent(pds->{
			log.info("데이터가 존재하므로");
			PDSFile target = new PDSFile();
			target.setFno(2L); // 2번 파일만 수정할거다~
			target.setPdsfile(newName);
			
			int idx = pds.getFiles().indexOf(target);
			// result가 pds에 들어있는 것(위에서 넣었음)
			log.info("idx:"+idx);
			if (idx > -1) { // -1보다 크다는 건 찾았다는 이야기~
				List<PDSFile> list = pds.getFiles();
				list.remove(idx); // 원래 있던 애는 지우고
				list.add(target); // 새로 만든 애를 추가함
				pds.setFiles(list);
			}
			repo.save(pds);
		});
	}
	
	@Test
	public void insertDummies() {
		List<PDSBoard> list = new ArrayList<>();
		IntStream.range(1,100).forEach(i->{
			PDSBoard pds = new PDSBoard();
			pds.setPname("자료 "+i);
			PDSFile file1 = new PDSFile();
			file1.setPdsfile("file1.doc");
			PDSFile file2 = new PDSFile();
			file2.setPdsfile("file2.doc");
			
			pds.setFiles(Arrays.asList(file1, file2));
			log.info("try to save pds");
			list.add(pds);
		});
		repo.saveAll(list);
	}
	
}






