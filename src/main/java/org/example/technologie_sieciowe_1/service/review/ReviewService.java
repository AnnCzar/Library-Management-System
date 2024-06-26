//package org.example.technologie_sieciowe_1.service.review;
//
//import org.example.technologie_sieciowe_1.commonTypes.UserRole;
//import org.example.technologie_sieciowe_1.controllers.dto.create.CreateReviewDto;
//import org.example.technologie_sieciowe_1.controllers.dto.get.GetReviewDto;
//import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateReviewResponseDto;
//import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
//import org.example.technologie_sieciowe_1.infrastructure.entity.LoanEntity;
//import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;
//import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
//import org.example.technologie_sieciowe_1.infrastructure.repositories.*;
//import org.example.technologie_sieciowe_1.service.auth_user.exceptions.UserNotFoundException;
//import org.example.technologie_sieciowe_1.service.book.exceptions.BookNotFoundException;
//import org.example.technologie_sieciowe_1.service.review.exceptions.ReviewNotFoundException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import java.util.stream.StreamSupport;
//
//@Service
//public class ReviewService {
//    private final ReviewRepository reviewRepository;
//    private final BookRepository bookRepository;
//    private final UserRepository userRepository;
//    private final AuthRepository authRepository;
//    private final LoanRepository loanRepository;
//
//    @Autowired
//    public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository, UserRepository userRepository, AuthRepository authRepository, LoanRepository loanRepository) {
//        this.reviewRepository = reviewRepository;
//        this.bookRepository = bookRepository;
//        this.userRepository = userRepository;
//        this.authRepository = authRepository;
//        this.loanRepository = loanRepository;
//    }
//    public List<GetReviewDto> getAll(String username) {
//
//        var auth = authRepository.findByUsername(username)
//                .orElseThrow(() -> UserNotFoundException.create(username));
//        var role = auth.getRole();
//
//        Iterable<ReviewEntity> reviews= reviewRepository.findAll();
//        List<GetReviewDto> rev = new ArrayList<>();
//        for (ReviewEntity review : reviews) {
//            rev.add(mapToGetReviewDto(review, role));
//        }
//        return rev;
//    }
//
//    public GetReviewDto getById(Integer id, String username){
//        GetReviewDto review = null;
//        var reviewEntity =  reviewRepository.findById(id).orElseThrow(() -> ReviewNotFoundException.create(id));
//        var auth = authRepository.findByUsername(username)
//                .orElseThrow(() -> UserNotFoundException.create(username));
//        var role = auth.getRole();
//
//
//        if (role == UserRole.ROLE_LIBRARIAN) {
//            review = new GetReviewDto(reviewEntity.getReviewID(),
//                    reviewEntity.getUser(),
//                    reviewEntity.getBook(),
//                    reviewEntity.getRate(),
//                    reviewEntity.getComment(),
//                    reviewEntity.getReviewDate());
//
//        } else if (role == UserRole.ROLE_READER){
//            review = new GetReviewDto(reviewEntity.getReviewID(),
//                    reviewEntity.getBook(),
//                    reviewEntity.getRate(),
//                    reviewEntity.getComment(),
//                    reviewEntity.getReviewDate());
//        }
//        return review;
//    }
//
//    public CreateReviewResponseDto add(CreateReviewDto review, String username) {
//
//        var auth = authRepository.findByUsername(username)
//                .orElseThrow(() -> UserNotFoundException.create(username));
//
//        Integer userId = auth.getId();
//
//        UserEntity user = userRepository.findById(userId).orElseThrow(() -> UserNotFoundException.create(userId));
//        BookEntity book = bookRepository.findById(review.getBook()).orElseThrow(BookNotFoundException::create);
//        List<LoanEntity> loans = loanRepository.findByUserId(userId);
//        List<Integer> booksIds = new ArrayList<>();
//        for (LoanEntity loan : loans) {  // to get user loanids
//            booksIds.add(loan.getBook().getId());
//        }
//        if(!booksIds.contains(review.getBook())){
//            throw ReviewNotFoundException.create(review.getBook());
//        }else {
//            var reviewEntity = new ReviewEntity();
//
//            reviewEntity.setBook(book);
//            reviewEntity.setUser(user);
//            reviewEntity.setRate(review.getRate());
//            reviewEntity.setComment(review.getComment());
//            reviewEntity.setReviewDate(review.getReviewDate());
//
//
//            var newReview =  reviewRepository.save(reviewEntity);
//            return new CreateReviewResponseDto(newReview.getReviewID(),
//                    newReview.getBook().getId(),
//                    newReview.getUser().getId(),
//                    newReview.getRate(),
//                    newReview.getComment(),
//                    newReview.getReviewDate());
//        }
//    }
//
//    public void delete(Integer id, String username) {
//        var auth = authRepository.findByUsername(username)
//                .orElseThrow(() -> UserNotFoundException.create(username));
//        var role = auth.getRole();
//        Integer userId = auth.getId();
//
//        List<ReviewEntity> reviews = reviewRepository.findByUserId(userId);
//        List<Integer> reviewIds = new ArrayList<>();
//        for (ReviewEntity review : reviews) {
//            reviewIds.add(review.getReviewID());
//        }
//
//        if(!reviewIds.contains(id)){
//            throw ReviewNotFoundException.create(id);
//        }
//        else if (role == UserRole.ROLE_LIBRARIAN){
//            reviewRepository.deleteById(id);
//        }else {
//            reviewRepository.deleteById(id);
//        }
//    }
//
//    public List<GetReviewDto> getByTitleOrBookId(Integer id, String username, Object object) {
//
//        var reviews =  reviewRepository.findAll();
//        List<GetReviewDto> rev = null;
//        if (object instanceof Integer) {
//            Integer bookId = (Integer) object;
//
//            List<ReviewEntity> booksId = new ArrayList<>();
//            for (ReviewEntity revs : reviews ) {
//                if (revs.getBook().getId().equals(bookId)) {
//                    booksId.add(revs);
//                }
//            }
//            rev = booksId.stream()
//                    .map(review -> new GetReviewDto(review.getReviewID(),
//                            review.getBook(),
//                            review.getRate(),
//                            review.getComment(),
//                            review.getReviewDate()))
//                    .toList();
//
//        } else if (object instanceof String) {
//            String bookTitle = (String) object;
//            List<ReviewEntity> booksTitles = new ArrayList<>();
//            for (ReviewEntity revs : reviews ) {
//                if (revs.getBook().getTitle().equals(bookTitle)) {
//                    booksTitles.add(revs);
//                }
//            }
//            rev = booksTitles.stream()
//                    .map(review -> new GetReviewDto(review.getReviewID(),
//                            review.getBook(),
//                            review.getRate(),
//                            review.getComment(),
//                            review.getReviewDate()))
//                    .toList();
//        }
//        return rev;
//    }
//    private GetReviewDto mapToGetReviewDto(ReviewEntity reviewEntity, UserRole role) {
//        if (role == UserRole.ROLE_LIBRARIAN) {
//            return new GetReviewDto(reviewEntity.getReviewID(),
//                    reviewEntity.getUser(),
//                    reviewEntity.getBook(),
//                    reviewEntity.getRate(),
//                    reviewEntity.getComment(),
//                    reviewEntity.getReviewDate());
//        } else {
//            return new GetReviewDto(reviewEntity.getReviewID(),
//                    reviewEntity.getBook(),
//                    reviewEntity.getRate(),
//                    reviewEntity.getComment(),
//                    reviewEntity.getReviewDate());
//        }
//    }
//
//}

package org.example.technologie_sieciowe_1.service.review;

import org.example.technologie_sieciowe_1.commonTypes.UserRole;
import org.example.technologie_sieciowe_1.controllers.dto.create.CreateReviewDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetReviewDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetUserDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDto;
import org.example.technologie_sieciowe_1.controllers.dto.respone.CreateReviewResponseDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.LoanEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.ReviewEntity;
import org.example.technologie_sieciowe_1.infrastructure.entity.UserEntity;
import org.example.technologie_sieciowe_1.infrastructure.repositories.*;
import org.example.technologie_sieciowe_1.service.auth_user.exceptions.UserNotFoundException;
import org.example.technologie_sieciowe_1.service.book.exceptions.BookNotFoundException;
import org.example.technologie_sieciowe_1.service.loan.LoanService;
import org.example.technologie_sieciowe_1.service.review.exceptions.ReviewNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final LoanRepository loanRepository;
    @Autowired
    private LoanService loanService;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, BookRepository bookRepository, UserRepository userRepository, AuthRepository authRepository, LoanRepository loanRepository) {
        this.reviewRepository = reviewRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.authRepository = authRepository;
        this.loanRepository = loanRepository;
    }

public List<GetReviewDto> getAll(String username) {
    var auth = authRepository.findByUsername(username)
            .orElseThrow(() -> UserNotFoundException.create(username));
    var role = auth.getRole();

    Iterable<ReviewEntity> reviews = reviewRepository.findAll();
    List<GetReviewDto> result = new ArrayList<>();
    for (ReviewEntity review : reviews) {
        GetBookDto bookDto = mapToBookDto(review.getBook());
        GetUserDto userDto = mapToUserDto(review.getUser());
        result.add(new GetReviewDto(
                review.getReviewID(),
                userDto,
                bookDto,
                review.getRate(),
                review.getComment(),
                review.getReviewDate()
        ));
    }
    return result;
}


    public GetReviewDto getById(Integer id, String username) {
        var reviewEntity = reviewRepository.findById(id).orElseThrow(() -> ReviewNotFoundException.create(id));
        var auth = authRepository.findByUsername(username).orElseThrow(() -> UserNotFoundException.create(username));
        var role = auth.getRole();
        return mapToGetReviewDto(reviewEntity, role);
    }

    public CreateReviewResponseDto add(CreateReviewDto review, String username) {
        var auth = authRepository.findByUsername(username).orElseThrow(() -> UserNotFoundException.create(username));
        Integer userId = auth.getUser().getId();
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> UserNotFoundException.create(userId));
        BookEntity book = bookRepository.findById(review.getBook()).orElseThrow(BookNotFoundException::create);
        if (!loanService.hasUserBorrowedBook(userId, book.getId())) {
            throw new IllegalStateException("User has not borrowed this book.");
        }


        var reviewEntity = new ReviewEntity();
        reviewEntity.setBook(book);
        reviewEntity.setUser(user);
        reviewEntity.setRate(review.getRate());
        reviewEntity.setComment(review.getComment());
        reviewEntity.setReviewDate(review.getReviewDate());

        var newReview = reviewRepository.save(reviewEntity);
        return new CreateReviewResponseDto(newReview.getReviewID(), newReview.getBook().getId(), newReview.getUser().getId(), newReview.getRate(), newReview.getComment(), newReview.getReviewDate());
    }

    public void delete(Integer id, String username) {
        var auth = authRepository.findByUsername(username).orElseThrow(() -> UserNotFoundException.create(username));
        var role = auth.getRole();
        if (!reviewRepository.existsById(id)) {
            throw ReviewNotFoundException.create(id);
        }
        if (role == UserRole.ROLE_LIBRARIAN) {
            reviewRepository.deleteById(id);
        } else {
            throw new UnsupportedOperationException("Readers cannot delete reviews.");
        }
    }

    private GetReviewDto mapToGetReviewDto(ReviewEntity reviewEntity, UserRole role) {
        GetBookDto bookDto = new GetBookDto(reviewEntity.getBook().getId(), reviewEntity.getBook().getTitle());  // Simplified example, add other book details as needed
        GetUserDto userDto = new GetUserDto(reviewEntity.getUser().getId(), reviewEntity.getUser().getusername());  // Simplified example, add other user details as needed

        return new GetReviewDto(reviewEntity.getReviewID(), userDto, bookDto, reviewEntity.getRate(), reviewEntity.getComment(), reviewEntity.getReviewDate());
    }



    private GetBookDto mapToBookDto(BookEntity book) {
        if (book == null) {
            return null;
        }
        return new GetBookDto(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getAuthor(),
                book.getPublisher(),
                book.getPublishYear(),
                book.getNumberCopy()
        );
    }

    private GetUserDto mapToUserDto(UserEntity user) {
        if (user == null) {
            return null;
        }
        return new GetUserDto(
                user.getId(),
                user.getusername(),
                user.getEmail(),
                user.getFullusername()
        );
    }

}
