package kosmo.test.testservice;

import kosmo.test.testdto.VideoDTO;

public interface TestService {
   boolean videosave(VideoDTO videoDTO);

   VideoDTO findvideo();
}
