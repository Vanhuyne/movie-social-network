export interface ReviewRequest {
    movieId: number;
    rating: number;
    comment: string;
}
export interface ReviewResponse{
    reviewId: number;
    userDisplayName: string;
    rating: number;
    comment: string;
    createdAt: string;
    updatedAt: string;
    upVotes : number;
    downVotes : number;
    userVote?: 'UP' | 'DOWN' | null;
}
export interface PageResponse<T> {
    content: T[];
    page: {
      size: number;
      number: number;
      totalElements: number;
      totalPages: number;
    };
  }