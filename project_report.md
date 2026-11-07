# ELEVORA
## Phase 1 – Complete Project Report
### AI-independent Professional Learning Platform

**Proposed tagline:** Learn. Grow. Lead.

**Flagship programme:** Managerial English Development Programme  
**Platforms:** iOS, Android and Web Admin Portal  
**Proposed launch subscription:** ₹99/month  
**Project phase:** Phase 1 – Non-AI Product  
**Document status:** Initial Product Requirements and Implementation Plan

---

# 1. Executive Summary

Elevora is a digital professional learning platform designed to help individuals improve their workplace communication, managerial capabilities, leadership, productivity and other professional skills.

The platform will initially launch with the Managerial English Development Programme, a structured six-month course containing:

- 24 weeks of learning.
- 72 lessons.
- 6 learning modules.
- Vocabulary development.
- Grammar and sentence construction.
- Workplace conversations and role-plays.
- Listening exercises.
- Speaking assignments.
- Weekly quizzes.
- Monthly assessments.
- Mid-course and final assessments.

The product will be available through native Android and iOS applications, supported by a centralized backend and an administrative web portal.

**The first phase will not use AI APIs, self-hosted LLMs, paid speech recognition or paid AI voice-generation services.**

All learning content, exercises, quizzes, assessments and reports will use predefined content and deterministic evaluation logic.

The platform will be designed to support additional courses in the future without requiring a major architectural redesign.

# 2. Product Vision

### Vision

To create an accessible digital platform that helps individuals develop practical professional skills through structured, interactive and measurable learning programmes.

### Mission

Make professional development affordable, practical and accessible through mobile-first learning.

### Core positioning

Elevora should not be positioned as another video-learning or PDF-reading application.

It should be positioned as a structured skill-development platform.

The central learner journey will be:

**Learn → Understand → Practise → Apply → Assess → Improve**

### Target audience

1. Working professionals.
2. Managers and team leaders.
3. Fresh graduates entering the workforce.
4. Students preparing for employment.
5. Entrepreneurs and business owners.
6. Individuals seeking better workplace communication.
7. Organizations interested in employee development.

# 3. Business Model

Elevora will operate as a multi-course subscription platform.

### Initial revenue model

| Plan | Proposed price | Description |
|---|---:|---|
| Free | ₹0 | Limited introductory content |
| Monthly Premium | ₹99/month | Access to the complete flagship course |
| Quarterly | ₹249/3 months | Discounted access |
| Half-yearly | ₹499/6 months | Access for the full programme duration |
| Annual | ₹899/year | Access to available premium courses, subject to plan policy |

These are proposed launch prices and should be validated through an initial customer pilot.

### Future revenue opportunities

- Additional individual courses.
- Corporate subscriptions.
- Institution and college subscriptions.
- Trainer-led batches.
- Bulk learner licences.
- Certificate programmes.
- Premium AI-powered coaching in a future phase.

# 4. Course Structure – Managerial English

The initial course will use the existing Managerial English Complete Training Package, Trainer Guide and Learner Book as the primary content sources.

### Programme details

| Parameter | Specification |
|---|---|
| Duration | 6 months |
| Total weeks | 24 |
| Total lessons | 72 |
| Classes per week | 3 |
| Suggested class duration | 60 minutes |
| Learning approach | Speaking-first and practical |
| Practice method | Workplace role-play |
| Primary outcome | Clearer and more confident managerial communication |

The Trainer Guide recommends a 70% speaking/practice and 30% language-input approach.

### Six learning modules

| Module | Module name | Lessons |
|---|---|---:|
| 1 | Building Confidence & Core Workplace English | 1–12 |
| 2 | Workplace Conversation & Team Communication | 13–24 |
| 3 | Managing People, Meetings & Performance | 25–36 |
| 4 | Emails, Reports & Presentations | 37–48 |
| 5 | Difficult Conversations, Complaints & Problem Solving | 49–60 |
| 6 | Leadership, Fluency & Workplace Performance | 61–72 |

### Course delivery

Each module will contain:

- Module introduction.
- Learning objectives.
- Twelve lessons.
- Vocabulary exercises.
- Language and grammar activities.
- Workplace scenarios.
- Speaking assignments.
- Practice activities.
- Module review.
- Assessment.

The course content should be entered and managed through the admin portal instead of being hardcoded into the mobile applications.

# 5. Mobile Application – Learner Features

## 5.1 Onboarding and authentication

Features:

- Welcome and introduction screens.
- Registration using email or mobile number.
- Login and logout.
- Forgot password.
- Profile creation.
- Learning goals.
- Current English proficiency selection.
- Course discovery.
- Subscription selection.

The application should support a unified account that can access multiple courses.

## 5.2 Home dashboard

The home screen should provide a clear overview of the learner's activity.

Information displayed:

- Greeting and profile.
- Current enrolled course.
- Overall programme progress.
- Current week and lesson.
- Today's learning activities.
- Continue learning button.
- Daily vocabulary challenge.
- Upcoming assessment.
- Weekly learning target.
- Recent performance.
- Learning streak.

Example:

**Good morning!**

Your Managerial English Programme

- Week 3 of 24.
- 18 of 72 lessons completed.
- Three activities scheduled this week.
- Next assessment: Week 4.

## 5.3 Course discovery

A dedicated course catalogue will allow learners to discover available programmes.

Course categories may include:

- Communication.
- Leadership.
- Business Management.
- Productivity.
- Career Development.
- Presentation Skills.
- Digital Skills.

Each course card should display:

- Course name.
- Course image.
- Short description.
- Duration.
- Number of modules.
- Number of lessons.
- Learning level.
- Course price.
- Enrolment status.

The catalogue must support courses being added later without app updates.

## 5.4 Learning path

The learner should see a structured progression.

Example:

Managerial English
- Module 1 – 12 lessons.
- Module 2 – 12 lessons.
- Module 3 – 12 lessons.
- Module 4 – 12 lessons.
- Module 5 – 12 lessons.
- Module 6 – 12 lessons.

Each module will show:

- Module description.
- Learning outcomes.
- Lesson completion.
- Assessment status.
- Module progress.

Lessons should be released according to the course configuration.

The admin should be able to configure whether lessons are immediately accessible or sequentially unlocked.

## 5.5 Lesson experience

Each lesson will contain the following sections.

### A. Learn

- Lesson introduction.
- Learning objectives.
- Explanation of the topic.
- Examples.
- Key concepts.
- Useful workplace phrases.

### B. Vocabulary

- Target vocabulary.
- Word meaning.
- Example sentence.
- Usage in workplace situations.
- Reference pronunciation audio where provided.

### C. Language focus

- Grammar concepts.
- Sentence construction.
- Correct and incorrect examples.
- Practical exercises.
- Sentence transformation.

### D. Listening

- Pre-recorded audio.
- Conversation scripts.
- Listening comprehension questions.
- Multiple-choice exercises.
- Correct-answer explanations.

### E. Practice

- Workplace situations.
- Guided conversations.
- Predefined role-play scenarios.
- Dialogue completion.
- Appropriate-response selection.

### F. Speak

- Speaking assignment.
- Instructions.
- Suggested speaking duration.
- Recording facility.
- Saved attempts.
- Playback.

### G. Homework

- Speaking assignment.
- Written exercise.
- Vocabulary practice.
- Reflection.
- Submission status.

The learner should be able to resume a partially completed lesson.

## 5.6 Vocabulary laboratory

A dedicated vocabulary section will help learners revise words across lessons.

Features:

- Vocabulary list.
- Word meaning.
- Example sentences.
- Audio pronunciation where available.
- Favourite words.
- Recently learned words.
- Revision exercises.
- Fill-in-the-blank exercises.
- Match-word-to-meaning exercises.
- Weekly vocabulary challenges.

The system should track which vocabulary exercises the learner has completed and their correctness.

## 5.7 Grammar laboratory

The grammar section will provide independent practice.

Features:

- Topic-wise grammar lessons.
- Explanations.
- Example sentences.
- Multiple-choice questions.
- Sentence correction.
- Fill-in-the-blank exercises.
- Sentence rearrangement.
- Correct-answer explanations.
- Topic-wise performance.

The admin should be able to associate grammar exercises with specific lessons.

## 5.8 Listening laboratory

This should be a major part of the learning experience.

Features:

- Pre-recorded workplace conversations.
- Professional dialogues.
- Meeting conversations.
- Telephone conversations.
- Client interactions.
- Manager-employee discussions.
- Listening comprehension questions.
- Replay controls.
- Conversation transcripts, where enabled.

Listening activities should be delivered using stored audio files rather than real-time speech services.

## 5.9 Workplace simulations

This feature will make Elevora more practical than a conventional learning app.

Learners should encounter realistic workplace situations.

Examples:

1. Introducing yourself professionally.
2. Explaining job responsibilities.
3. Asking for clarification.
4. Giving instructions.
5. Delegating tasks.
6. Conducting meetings.
7. Providing positive feedback.
8. Handling corrective feedback.
9. Responding to complaints.
10. Negotiating with clients.
11. Discussing performance.
12. Presenting a business update.

### Simulation formats

- Select the most professional response.
- Complete the conversation.
- Arrange the dialogue.
- Identify inappropriate statements.
- Choose the next response.
- Listen to a predefined conversation.
- Practise a dialogue independently.

These will be predefined activities, not AI-generated conversations.

## 5.10 Speaking practice and recording

Speaking practice should support the programme's practical learning methodology.

Features:

- Speaking task instructions.
- Reference questions.
- Suggested duration.
- Microphone recording.
- Pause and resume.
- Stop and save.
- Playback.
- Multiple attempts.
- Rename and delete recordings.
- Associate recordings with lessons.
- Recording history.

### Recording implementation

For iOS:

- AVAudioRecorder for audio capture.
- AVAudioPlayer for playback.

For Android:

- Android native audio recording APIs.
- Native playback capabilities.

Recordings may initially be stored locally, with optional cloud backup according to the product's storage policy.

The app must request microphone permission and explain its purpose.

Recordings must not be automatically evaluated or presented as objectively assessed speech scores.

## 5.11 Assessments

The assessment system should combine automatically evaluated exercises with clearly labelled self-assessments and optional trainer evaluations.

### Assessment schedule

| Assessment | Timing |
|---|---|
| Diagnostic assessment | Class 1 |
| Weekly mini-quiz | End of each week |
| Monthly speaking assessment | Weeks 4, 8, 12, 16 and 20 |
| Mid-course assessment | End of Month 3 |
| Final assessment | Classes 71–72 |

### Weekly quiz

The supplied training package specifies:

- Five objective questions.
- One two-minute speaking challenge.

The objective questions can be evaluated automatically.

The speaking challenge can be recorded and submitted or self-reviewed.

### Monthly speaking assessment

The Trainer Guide specifies five observation dimensions rated from 1–5:

1. Fluency.
2. Accuracy.
3. Pronunciation.
4. Vocabulary.
5. Interaction.

For a self-learning subscription, these should be presented as learner self-assessment unless a trainer actually evaluates the recording.

### Final assessment

The Trainer Guide suggests this weighting:

| Assessment area | Weight |
|---|---:|
| Speaking | 40% |
| Listening/Reading | 20% |
| Writing | 20% |
| Vocabulary/Grammar | 20% |
| Total | 100% |

Speaking scores should be trainer-assigned or explicitly identified as self-assessed.

Objective quiz results should be calculated automatically.

### Assessment result

The learner should receive:

- Marks obtained.
- Percentage.
- Correct and incorrect answers.
- Topic-wise performance.
- Areas requiring revision.
- Previous assessment comparison.
- Recommended next activities.

## 5.12 Progress tracking

The progress system should include:

- Overall course completion.
- Module completion.
- Lesson completion.
- Quiz performance.
- Vocabulary performance.
- Grammar performance.
- Listening performance.
- Writing assessment results.
- Speaking assignment completion.
- Assessment history.
- Learning streak.
- Weekly activity.

### Progress comparison

The system should compare:

- Initial diagnostic assessment.
- Monthly results.
- Mid-course results.
- Final results.

This will help learners understand their progress over the six-month programme.

Importantly, the dashboard must distinguish between:

- Objective scores.
- Trainer-assigned scores.
- Learner self-assessment.
- Lesson completion.

These are different types of measurements and must not be combined misleadingly.

## 5.13 Notifications and reminders

Notifications should include:

- Daily learning reminders.
- Pending lessons.
- Upcoming quizzes.
- Assessment reminders.
- New course announcements.
- Subscription expiry.
- Learning streak reminders.
- Course completion notifications.

Push notifications can be implemented using Firebase Cloud Messaging and Apple Push Notification service.

## 5.14 Profile and settings

Features:

- Personal information.
- Profile photograph.
- Learning goals.
- Enrolled courses.
- Subscription details.
- Payment history.
- Saved recordings.
- Downloaded resources.
- Notification preferences.
- Privacy settings.
- Account deletion.
- Help and support.
- Logout.

# 6. Web Administration Portal

The admin portal will control the entire learning platform.

It should be designed as a multi-course management system from the beginning.

## 6.1 Admin dashboard

Display:

- Total registered learners.
- Active subscribers.
- New registrations.
- Course enrolments.
- Monthly revenue.
- Course completion.
- Quiz performance.
- Subscription renewals.
- Expiring subscriptions.

## 6.2 Course management

Admin should be able to:

- Create a course.
- Edit course details.
- Upload course cover images.
- Configure duration.
- Configure course pricing.
- Set learning level.
- Add learning objectives.
- Publish or unpublish courses.
- Set free or premium status.

## 6.3 Module management

Features:

- Create modules.
- Edit module details.
- Upload module images.
- Set module order.
- Associate lessons.
- Configure unlock rules.
- Publish or unpublish modules.

## 6.4 Lesson management

The lesson editor should support:

- Lesson title.
- Description.
- Learning objectives.
- Rich-text content.
- Images.
- Audio.
- Video.
- Vocabulary.
- Grammar content.
- Useful phrases.
- Role-play scenarios.
- Exercises.
- Homework.
- Speaking assignments.
- Lesson attachments.

Admin should be able to reorder content and preview lessons before publishing.

## 6.5 Question and quiz management

The admin should be able to create:

- Multiple-choice questions.
- Multiple-select questions.
- True/false questions.
- Fill-in-the-blank questions.
- Match-the-following exercises.
- Sentence ordering exercises.
- Dialogue completion exercises.

Question configuration:

- Question text.
- Answer options.
- Correct answer.
- Explanation.
- Marks.
- Difficulty.
- Associated lesson.
- Associated topic.
- Quiz duration.

The quiz engine should randomize questions and options where configured.

## 6.6 Assessment management

Features:

- Create assessment templates.
- Configure assessment dates.
- Select questions.
- Configure marks.
- Configure passing criteria.
- View submissions.
- View results.
- Record trainer evaluations.
- Generate reports.

## 6.7 Learner management

Admin should be able to:

- View registered learners.
- View learner profiles.
- View enrolled courses.
- View subscription status.
- View learning progress.
- View quiz results.
- View assessment history.
- View submitted assignments.
- Suspend or activate accounts.

## 6.8 Trainer management

Although the first phase can focus on self-learning, trainer functionality should be included in the underlying architecture.

Trainer features:

- Create trainer accounts.
- Assign trainers to courses.
- Assign trainers to batches.
- View assigned learners.
- Review speaking recordings with appropriate access permission.
- Provide assessment ratings.
- Add feedback.
- Track learner performance.

Trainer-led classes and batch scheduling can be enabled in a later release without rebuilding the course engine.

## 6.9 Subscription and payment management

Features:

- Configure subscription plans.
- Configure prices.
- Manage free and premium access.
- View payment transactions.
- View renewals.
- View failed payments.
- Handle refunds.
- Manage subscription expiry.
- View revenue reports.

Payment implementation should comply with the applicable Apple App Store and Google Play billing policies for digital subscriptions. Direct web payments can be supported where permitted.

## 6.10 Content media management

The admin portal should support:

- Images.
- Audio files.
- Video files.
- PDF resources.
- Lesson attachments.

Media should be stored in object storage or a suitable cloud file-storage service rather than directly inside PostgreSQL.

# 7. Technical Architecture

The system should be designed for future expansion into additional courses and AI-powered features.

## 7.1 Recommended technology stack

| Layer | Technology |
|---|---|
| iOS application | SwiftUI |
| Android application | Kotlin + Jetpack Compose |
| Backend | Node.js + Express |
| Database | PostgreSQL |
| Authentication | Firebase Authentication or secure backend authentication |
| Admin portal | React + Vite |
| File storage | S3-compatible object storage |
| Push notifications | Firebase Cloud Messaging / APNs |
| Payment | Store billing and supported payment gateway |
| Hosting | Linux VPS / cloud infrastructure |
| API communication | REST APIs |

## 7.2 High-level architecture

```text
                 ELEVORA PLATFORM
                        |
           ---------------------------
           |                         |
      Mobile Apps                Admin Portal
           |                         |
     iOS / Android                React Web
           |                         |
           -------- REST API --------
                        |
                 Node.js Backend
                        |
        --------------------------------
        |              |               |
    PostgreSQL     File Storage     Notifications
        |              |               |
    Course Data     Audio/Video       FCM/APNs
    User Data       Images
    Assessments     Documents
    Payments
    Progress
```

## 7.3 Core backend modules

The backend should be separated into logical modules:

1. Authentication.
2. User management.
3. Course catalogue.
4. Course enrolment.
5. Module management.
6. Lesson management.
7. Content delivery.
8. Vocabulary management.
9. Grammar exercises.
10. Quiz engine.
11. Assessment engine.
12. Speaking assignment management.
13. Recording metadata.
14. Progress tracking.
15. Subscription management.
16. Payment management.
17. Notifications.
18. Trainer management.
19. Reporting and analytics.
20. Admin permissions.

## 7.4 Suggested database entities

| Entity | Purpose |
|---|---|
| users | Learner and staff accounts |
| roles | Role definitions |
| user_profiles | Learner information |
| courses | Course catalogue |
| modules | Course modules |
| lessons | Individual lessons |
| lesson_sections | Structured lesson content |
| lesson_resources | Images, audio and attachments |
| vocabulary | Words and meanings |
| grammar_topics | Grammar lessons |
| exercises | Practice activities |
| questions | Quiz questions |
| question_options | Answer options |
| quizzes | Quiz configuration |
| quiz_attempts | Learner quiz attempts |
| quiz_responses | Individual answers |
| assignments | Speaking and written assignments |
| recordings | Recording metadata and file references |
| assessments | Assessment configuration |
| assessment_attempts | Assessment results |
| trainer_reviews | Trainer feedback |
| learner_progress | Learning completion data |
| subscriptions | Subscription records |
| payments | Payment transactions |
| notifications | Notification records |
| trainers | Trainer profiles |
| batches | Future trainer-led batches |
| batch_enrolments | Batch learner mapping |

The database should use stable identifiers and maintain the relationship between a learner, course, module, lesson and assessment.

# 8. Non-AI Product Scope

This section defines what Phase 1 explicitly excludes.

### Excluded features

- AI speaking partner.
- AI-generated conversations.
- AI grammar correction.
- AI writing correction.
- AI pronunciation evaluation.
- AI-generated learning plans.
- Self-hosted LLM.
- Paid LLM APIs.
- Cloud speech-to-text APIs.
- Cloud text-to-speech APIs.
- Automated semantic evaluation of free-form speaking.
- AI-based learner recommendations.

### Included non-AI alternatives

| Requirement | Phase 1 implementation |
|---|---|
| Pronunciation examples | Pre-recorded reference audio |
| Speaking practice | Device-based recording |
| Speaking evaluation | Self-assessment or trainer review |
| Grammar correction | Predefined exercises and explanations |
| Writing evaluation | Rubric-based trainer review |
| Conversation practice | Predefined dialogues and role-play |
| Learning recommendations | Rule-based recommendations |
| Progress reports | Calculated from actual assessment records |
| Voice playback | Native device audio playback |

Native device text-to-speech may be used as an optional accessibility feature, but the core product must not depend on a cloud speech service.

# 9. Security and Privacy

The product will store learner information, subscription details, assessment records and potentially personal voice recordings.

Required safeguards:

- Secure authentication.
- Role-based access control.
- Encrypted network communication.
- Secure password handling.
- Protected file URLs.
- Recording access restrictions.
- Learner consent before recording.
- Clear recording retention policy.
- Account deletion.
- Secure payment verification.
- Regular database backups.
- Administrative activity logs.
- Rate limiting and API protection.

Voice recordings must not be made public or accessible to other learners.

The learner should be able to delete recordings, subject to clearly disclosed backup and retention rules.

# 10. UI/UX Design Direction

Elevora should have a premium professional appearance.

### Recommended design identity

- Midnight navy.
- Champagne gold.
- White and muted grey for content surfaces.
- Professional typography.
- Subtle gradients.
- Premium course photography.
- Clean data visualization.
- Refined icons.
- Smooth transitions.

The app should feel like a professional development platform rather than a children's language-learning application.

### Main mobile navigation

Recommended five-tab structure:

1. Home.
2. Learn.
3. Practice.
4. Assess.
5. Profile.

The Courses catalogue can be accessed from Home and Learn.

### Key mobile screens

1. Splash screen.
2. Onboarding.
3. Login and registration.
4. Home dashboard.
5. Course catalogue.
6. Course details.
7. Learning path.
8. Module overview.
9. Lesson details.
10. Vocabulary practice.
11. Grammar practice.
12. Listening practice.
13. Workplace simulation.
14. Speaking recording.
15. Recording history.
16. Weekly quiz.
17. Assessment screen.
18. Assessment results.
19. Progress dashboard.
20. Course completion.
21. Profile.
22. Subscription.
23. Payment history.
24. Notifications.
25. Settings.

# 11. Pricing and Revenue Model

## 11.1 Individual subscription

The proposed initial monthly price is ₹99.

The annual and multi-month plans should encourage learners to commit to the full learning programme.

## 11.2 Revenue projections

The following table shows gross collections assuming every paying subscriber purchases the ₹99 monthly plan.

| Paying subscribers | Monthly gross revenue | Annualized gross revenue |
|---:|---:|---:|
| 100 | ₹9,900 | ₹1,18,800 |
| 500 | ₹49,500 | ₹5,94,000 |
| 1,000 | ₹99,000 | ₹11,88,000 |
| 2,500 | ₹2,47,500 | ₹29,70,000 |
| 5,000 | ₹4,95,000 | ₹59,40,000 |
| 10,000 | ₹9,90,000 | ₹1,18,80,000 |

These are gross revenue illustrations, not profit projections.

## 11.3 Other revenue sources

### Corporate subscriptions

Organizations can purchase subscriptions for employees.

Possible features:

- Bulk learner enrolment.
- Organization dashboard.
- Employee progress.
- Course completion reports.
- Batch-level reports.
- Corporate billing.

Corporate pricing should be determined separately based on usage, support and reporting requirements.

### Institutional partnerships

Colleges, training institutes and placement organizations can purchase bulk access for students.

### Additional courses

The same platform can sell multiple courses under one account.

# 12. Estimated Development Budget

The following is an indicative development budget assuming an AI-assisted internal development process and reuse of existing course material.

| Development component | Estimated cost |
|---|---:|
| Product planning and UI/UX | ₹10,000–15,000 |
| iOS application | ₹25,000–35,000 |
| Android application | ₹25,000–35,000 |
| Backend and database | ₹20,000–30,000 |
| Admin and trainer portal | ₹20,000–25,000 |
| Payment and notification integration | ₹5,000–10,000 |
| Testing and deployment | ₹10,000–15,000 |
| **Estimated total** | **₹1,15,000–1,65,000** |

These are planning estimates, not quotations. They exclude the cost of producing substantial new course content, professional voice recordings, third-party legal review and marketing.

# 13. Estimated Monthly Operating Expenses

A lean initial deployment can use the following planning budget.

| Expense | Estimated monthly cost |
|---|---:|
| Application server / VPS | ₹1,500 |
| Database and backups | ₹500 |
| File storage and bandwidth | ₹500–1,500 |
| Notifications and email | ₹300–500 |
| Monitoring and miscellaneous services | ₹500 |
| Maintenance allowance | ₹3,000–5,000 |
| **Total** | **₹6,300–9,000** |

These figures are estimates. Actual costs will depend on learner numbers, audio/video consumption, recording storage, bandwidth and infrastructure choices.

There will be no recurring AI inference expense in this phase.

# 14. Development Timeline

A realistic target is approximately 10–12 weeks, subject to content readiness and testing.

| Stage | Work | Estimated duration |
|---|---|---:|
| 1 | Requirement finalization and UI/UX | 1–2 weeks |
| 2 | Backend and database foundation | 2 weeks |
| 3 | Course and lesson management | 2 weeks |
| 4 | Android and iOS learner applications | 3–4 weeks |
| 5 | Quiz, assessment and progress systems | 1–2 weeks |
| 6 | Payment, testing and deployment | 1–2 weeks |

Some activities can run in parallel.

## Development milestones

### Milestone 1 – Foundation

- Project setup.
- Authentication.
- Database.
- User profiles.
- Admin roles.
- Course catalogue.

### Milestone 2 – Learning engine

- Course management.
- Module management.
- Lesson management.
- Learning content.
- Vocabulary and grammar.

### Milestone 3 – Practice engine

- Listening.
- Workplace simulations.
- Speaking assignments.
- Audio recording.
- Recording history.

### Milestone 4 – Assessment engine

- Quiz builder.
- Quiz attempts.
- Assessment management.
- Progress calculation.
- Reports.

### Milestone 5 – Commercial launch

- Subscription plans.
- Payment integration.
- Notifications.
- Security testing.
- App Store and Play Store preparation.
- Pilot testing.

# 15. Testing and Quality Assurance

Before launch, test the following areas.

### Mobile application

- Registration and login.
- Course enrolment.
- Lesson navigation.
- Lesson completion.
- Audio playback.
- Microphone permissions.
- Recording and playback.
- Offline recording behavior.
- Quiz submission.
- Assessment results.
- Subscription purchases.
- Payment failure handling.

### Admin portal

- Course creation.
- Module creation.
- Lesson publishing.
- Media uploads.
- Question creation.
- Assessment configuration.
- Learner management.
- Trainer access.
- Reports.

### Backend

- Authentication and authorization.
- Data validation.
- Payment verification.
- API rate limiting.
- Backup and recovery.
- File security.
- Performance under concurrent users.

### Pilot testing

Before the public launch, conduct a pilot with approximately 20–50 learners.

Collect feedback on:

- Lesson quality.
- Ease of navigation.
- Exercise usefulness.
- Speaking assignments.
- Assessment fairness.
- Course completion.
- Willingness to pay.

# 16. Future Expansion – Phase 2 and Beyond

Phase 1 should be developed so that AI integration can be added later without redesigning the entire platform.

### Phase 2 – AI-assisted learning

Potential additions:

- AI speaking partner.
- AI workplace role-play.
- Grammar correction.
- Sentence improvement.
- AI writing assistant.
- AI pronunciation analysis.
- Personalized learning recommendations.

The architecture should allow these features to be integrated through a separate AI service layer.

### Phase 3 – Multi-course ecosystem

Potential courses:

- Business Communication.
- Leadership Development.
- Public Speaking.
- Presentation Skills.
- Time Management.
- Productivity.
- Business Management.
- Professional Writing.
- Interview Preparation.
- Career Development.

### Phase 4 – Corporate learning platform

- Organization accounts.
- HR dashboard.
- Employee enrolment.
- Corporate learning paths.
- Team performance.
- Trainer-led batches.
- Organization-level reporting.
- Corporate billing.

# 17. Key Business Metrics

The admin dashboard should track:

| Metric | Why it matters |
|---|---|
| Registrations | Acquisition |
| Free-to-paid conversion | Monetization |
| Monthly active learners | Engagement |
| Lessons completed | Learning activity |
| Weekly quiz participation | Practice consistency |
| Course completion rate | Learning retention |
| Monthly recurring revenue | Business growth |
| Subscription renewal | Customer retention |
| Refund rate | Product satisfaction |
| Average revenue per subscriber | Monetization efficiency |

The most important initial metrics should be paid conversion, weekly active learners, lesson completion and subscription renewal.

# 18. Risks and Mitigation

| Risk | Mitigation |
|---|---|
| Learners do not complete the programme | Daily tasks, reminders and structured milestones |
| Learners expect AI feedback | Clearly communicate Stage 1 capabilities |
| Audio storage costs increase | Retention limits, compression and storage policies |
| Content becomes difficult to maintain | Admin-driven content management |
| Low subscription conversion | Free introductory lessons and pilot testing |
| Learners lose interest | Weekly challenges, quizzes and progress |
| Inconsistent assessment quality | Standardized rubrics and objective quiz scoring |
| Expansion becomes expensive | Multi-course architecture from day one |

# 19. Phase 1 Deliverables

The Phase 1 project will be considered ready for launch when the following are completed.

### Learner applications

- Native iOS application.
- Native Android application.
- Authentication.
- Course catalogue.
- Course enrolment.
- 72 Managerial English lessons.
- Six modules.
- Vocabulary and grammar practice.
- Listening activities.
- Workplace simulations.
- Speaking recording.
- Weekly quizzes.
- Monthly assessments.
- Mid-course and final assessment.
- Progress dashboard.
- Subscription and payments.
- Notifications.
- Profile and settings.

### Admin portal

- Dashboard.
- Course management.
- Module management.
- Lesson management.
- Content and media management.
- Vocabulary management.
- Grammar management.
- Exercise management.
- Quiz builder.
- Assessment management.
- Learner management.
- Trainer management.
- Subscription management.
- Payment reports.
- Performance reports.

### Backend

- Authentication.
- Role-based access.
- Course engine.
- Content engine.
- Practice engine.
- Assessment engine.
- Progress engine.
- Subscription engine.
- Notification engine.
- File storage integration.
- Reporting APIs.

### Explicit launch exclusions

- AI APIs.
- Self-hosted LLM.
- AI speech recognition.
- AI voice generation.
- AI evaluation.

# 20. Final Recommendation

Elevora should be launched as a complete professional learning platform, with Managerial English as the first flagship programme.

The initial product should provide genuine learning value through structured lessons, practical exercises, workplace scenarios, listening activities, assessments and visible progress.

The ₹99 subscription should be treated as an accessible entry-level product, not as a payment for access to recordings or static documents.

The platform's architecture should be multi-course from the beginning, while its initial content and marketing focus should remain concentrated on Managerial English.

**The strategic principle is simple:**

Build a product that learners find valuable without AI. Once there is evidence of demand, introduce AI as an additional premium capability rather than making the business dependent on AI expenditure from its first day.

---

**End of Phase 1 Project Report**

**Product:** Elevora  
**Flagship Course:** Managerial English Development Programme  
**Launch Model:** Subscription-based professional learning platform  
**Proposed Price:** ₹99/month  
**AI Dependency:** None  
**Platforms:** Android, iOS and Web Admin Portal

