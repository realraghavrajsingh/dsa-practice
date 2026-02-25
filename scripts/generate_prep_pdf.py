#!/usr/bin/env python3
"""
Generate a beautifully designed PDF from Sigmoid Technical Lead prep content.
"""

from reportlab.lib import colors
from reportlab.lib.pagesizes import letter
from reportlab.lib.styles import getSampleStyleSheet, ParagraphStyle
from reportlab.lib.units import inch
from reportlab.platypus import (
    SimpleDocTemplate, Paragraph, Spacer, Table, TableStyle,
    PageBreak, ListFlowable, ListItem
)
from reportlab.lib.enums import TA_LEFT, TA_CENTER, TA_RIGHT
from xml.sax.saxutils import escape
from pathlib import Path


def add_header_footer(canvas, doc):
    """Add header bar and page number to each page."""
    canvas.saveState()
    # Header accent bar
    canvas.setFillColor(ACCENT_TEAL)
    canvas.rect(0, letter[1] - 0.15 * inch, letter[0], 0.15 * inch, fill=1, stroke=0)
    canvas.setFillColor(colors.white)
    canvas.setFont("Helvetica-Bold", 9)
    canvas.drawString(0.75 * inch, letter[1] - 0.35 * inch, "Sigmoid Technical Lead | Interview Prep")
    # Page number
    canvas.setFillColor(MEDIUM_GRAY)
    canvas.setFont("Helvetica", 9)
    canvas.drawRightString(letter[0] - 0.75 * inch, 0.5 * inch, f"Page {canvas.getPageNumber()}")
    canvas.restoreState()

# Colors - professional tech palette
DARK_NAVY = colors.HexColor("#1a365d")
ACCENT_TEAL = colors.HexColor("#0d9488")
LIGHT_GRAY = colors.HexColor("#f8fafc")
MEDIUM_GRAY = colors.HexColor("#64748b")
DARK_TEXT = colors.HexColor("#1e293b")
ACCENT_LIGHT = colors.HexColor("#ccfbf1")


def build_styles():
    styles = getSampleStyleSheet()
    styles.add(ParagraphStyle(
        name="DocTitle",
        parent=styles["Heading1"],
        fontSize=24,
        textColor=DARK_NAVY,
        spaceAfter=6,
        alignment=TA_CENTER,
        fontName="Helvetica-Bold",
    ))
    styles.add(ParagraphStyle(
        name="DocSubtitle",
        parent=styles["Normal"],
        fontSize=12,
        textColor=MEDIUM_GRAY,
        spaceAfter=24,
        alignment=TA_CENTER,
    ))
    styles.add(ParagraphStyle(
        name="DocSectionHeader",
        parent=styles["Heading1"],
        fontSize=16,
        textColor=DARK_NAVY,
        spaceBefore=20,
        spaceAfter=10,
        borderPadding=(0, 0, 4, 0),
        borderColor=ACCENT_TEAL,
        borderWidth=0,
        leftIndent=0,
        fontName="Helvetica-Bold",
    ))
    styles.add(ParagraphStyle(
        name="DocSubSectionHeader",
        parent=styles["Heading2"],
        fontSize=13,
        textColor=ACCENT_TEAL,
        spaceBefore=14,
        spaceAfter=8,
        fontName="Helvetica-Bold",
    ))
    styles.add(ParagraphStyle(
        name="DocBody",
        parent=styles["Normal"],
        fontSize=10,
        textColor=DARK_TEXT,
        spaceAfter=6,
        leading=14,
    ))
    styles.add(ParagraphStyle(
        name="DocBullet",
        parent=styles["Normal"],
        fontSize=10,
        textColor=DARK_TEXT,
        leftIndent=20,
        spaceAfter=4,
        bulletIndent=10,
        leading=13,
    ))
    styles.add(ParagraphStyle(
        name="DocItalic",
        parent=styles["Normal"],
        fontSize=10,
        textColor=MEDIUM_GRAY,
        fontName="Helvetica-Oblique",
        spaceAfter=8,
    ))
    styles.add(ParagraphStyle(
        name="DocCode",
        parent=styles["Normal"],
        fontSize=9,
        textColor=DARK_TEXT,
        backColor=LIGHT_GRAY,
        borderPadding=12,
        leftIndent=12,
        rightIndent=12,
        spaceBefore=8,
        spaceAfter=8,
        fontName="Courier",
    ))
    styles.add(ParagraphStyle(
        name="DocChecklist",
        parent=styles["Normal"],
        fontSize=10,
        textColor=DARK_TEXT,
        leftIndent=20,
        spaceAfter=3,
        leading=13,
    ))
    styles.add(ParagraphStyle(
        name="TableCell",
        parent=styles["Normal"],
        fontSize=9,
        textColor=DARK_TEXT,
        leading=12,
        wordWrap="CJK",
    ))
    styles.add(ParagraphStyle(
        name="TableCellHeader",
        parent=styles["Normal"],
        fontSize=10,
        textColor=colors.white,
        fontName="Helvetica-Bold",
        leading=12,
        wordWrap="CJK",
    ))
    return styles


def _cell_content(cell, styles, is_header=False):
    """Convert cell to Paragraph for proper text wrapping."""
    if hasattr(cell, "getAlign"):
        return cell  # Already a flowable
    text = str(cell) if cell else ""
    safe = escape(text).replace("->", "&#8594;")  # arrow for XML safety
    style = "TableCellHeader" if is_header else "TableCell"
    return Paragraph(safe, styles[style])


def create_table(data, col_widths=None, styles=None):
    """Create table with wrapped text in cells."""
    if styles is None:
        styles = getSampleStyleSheet()
    wrapped_data = []
    for row_idx, row in enumerate(data):
        wrapped_row = [
            _cell_content(cell, styles, is_header=(row_idx == 0))
            for cell in row
        ]
        wrapped_data.append(wrapped_row)
    t = Table(wrapped_data, colWidths=col_widths, repeatRows=1)
    t.setStyle(TableStyle([
        ("BACKGROUND", (0, 0), (-1, 0), ACCENT_TEAL),
        ("TEXTCOLOR", (0, 0), (-1, 0), colors.white),
        ("ALIGN", (0, 0), (-1, -1), "LEFT"),
        ("VALIGN", (0, 0), (-1, -1), "TOP"),
        ("FONTNAME", (0, 0), (-1, 0), "Helvetica-Bold"),
        ("FONTSIZE", (0, 0), (-1, 0), 10),
        ("BOTTOMPADDING", (0, 0), (-1, 0), 10),
        ("TOPPADDING", (0, 0), (-1, 0), 10),
        ("BACKGROUND", (0, 1), (-1, -1), colors.white),
        ("TEXTCOLOR", (0, 1), (-1, -1), DARK_TEXT),
        ("FONTSIZE", (0, 1), (-1, -1), 9),
        ("GRID", (0, 0), (-1, -1), 0.5, colors.HexColor("#e2e8f0")),
        ("ROWBACKGROUNDS", (0, 1), (-1, -1), [colors.white, LIGHT_GRAY]),
        ("LEFTPADDING", (0, 0), (-1, -1), 10),
        ("RIGHTPADDING", (0, 0), (-1, -1), 10),
        ("TOPPADDING", (0, 0), (-1, -1), 8),
        ("BOTTOMPADDING", (0, 0), (-1, -1), 8),
    ]))
    return t


def main():
    output_dir = Path.cwd()
    output_path = output_dir / "Sigmoid-Technical-Lead-Prep.pdf"

    doc = SimpleDocTemplate(
        str(output_path),
        pagesize=letter,
        rightMargin=0.75 * inch,
        leftMargin=0.75 * inch,
        topMargin=0.75 * inch,
        bottomMargin=0.75 * inch,
    )

    styles = build_styles()
    story = []

    # Title
    story.append(Spacer(1, 0.3 * inch))
    story.append(Paragraph("Sigmoid Technical Lead", styles["DocTitle"]))
    story.append(Paragraph("Data Engineering | Interview Prep & Application Materials", styles["DocSubtitle"]))
    story.append(Spacer(1, 0.2 * inch))

    # --- Section 1: Interview Preparation ---
    story.append(Paragraph("1. Interview Preparation", styles["DocSectionHeader"]))

    story.append(Paragraph("Common Behavioral Questions", styles["DocSubSectionHeader"]))
    behavioral_data = [
        ["Question", "What They're Assessing", "Strong Answer Framework"],
        [
            "Tell me about a time you designed a scalable distributed system.",
            "Architecture skills, ownership",
            "Situation -> Task -> Action (tech decisions, trade-offs) -> Result (scale, latency, reliability metrics)",
        ],
        [
            "How do you balance hands-on coding with technical leadership?",
            "Dual role fit",
            "Describe a cadence (e.g., 60% coding, 40% design/reviews/mentoring) with a concrete example",
        ],
        [
            "Describe translating business requirements into a technical solution for a senior stakeholder.",
            "Client engagement, communication",
            "Pick a VP/Director-level interaction; show how you aligned tech choices with business outcomes",
        ],
        [
            "How have you mentored or grown engineers on your team?",
            "Leadership, hiring",
            "Specific examples: pair programming, design reviews, career conversations, hiring process",
        ],
        [
            "Tell me about a failure or project that didn't go as planned. What did you learn?",
            "Ownership, growth mindset",
            "Honest story + clear lessons + how you applied them",
        ],
        [
            "Why Sigmoid? Why this role?",
            "Fit, motivation",
            "Connect to: data engineering at scale, Fortune 500 exposure, startup culture, big data problems",
        ],
    ]
    story.append(create_table(behavioral_data, col_widths=[2.2 * inch, 1.5 * inch, 3.3 * inch], styles=styles))
    story.append(Spacer(1, 16))

    story.append(Paragraph("Elaborated Answers (Tailored to Your Experience)", styles["DocSubSectionHeader"]))
    story.append(Paragraph(
        "Use these STAR-style answers as talking points. Customize with specific metrics and names where possible.",
        styles["DocItalic"]
    ))
    story.append(Spacer(1, 8))

    elaborated = [
        (
            "1. Tell me about a time you designed a scalable distributed system.",
            "At NCR Voyix, I architected and implemented scalable Java backend applications using Spring Boot "
            "and RESTful APIs for microservices communication. I managed containerized applications with Docker "
            "and Kubernetes on GCP, overseeing cluster deployments. I integrated Kafka and Google Cloud Pub/Sub "
            "for messaging, and optimized PostgreSQL for performance. The system supports efficient microservices "
            "communication and accelerated release cycles through CI/CD pipelines I designed. At UST Global, I "
            "worked on Scala, Spark, and Kafka for streaming and processing large datasets, giving me hands-on "
            "experience with distributed data processing."
        ),
        (
            "2. How do you balance hands-on coding with technical leadership?",
            "I maintain roughly 60% hands-on and 40% design/reviews/mentoring. At NCR Voyix, I develop scalable "
            "Java applications and architect CI/CD pipelines while also configuring SonarQube to enforce code "
            "quality and reduce technical debt. I integrate AI-driven tools (Cursor, Copilot, Gemini, Claude) to "
            "increase team productivity. At Walmart and Dell, I designed JUnit classes and integration tests, "
            "created technical documentation on Confluence, and managed SDLC including code merging and automated "
            "testing. I lead by example in code quality and share knowledge through documentation."
        ),
        (
            "3. Describe translating business requirements into a technical solution for a senior stakeholder.",
            "At Walmart and Dell (Fortune 500), I worked in Agile environments using Scrum/JIRA to deliver "
            "features aligned with business priorities. I created detailed technical documentation on Confluence "
            "to bridge business and engineering. At NCR Voyix, I translate requirements into RESTful API design "
            "and microservices architecture. I present technical choices in terms of business outcomes: faster "
            "releases via CI/CD, reliability through containerization, and scalability via GCP and Kubernetes. "
            "I focus on clear communication and aligning tech decisions with stakeholder goals."
        ),
        (
            "4. How have you mentored or grown engineers on your team?",
            "I mentor through code reviews, documentation, and quality standards. At NCR Voyix, I configured "
            "SonarQube and integrated it into the build process, teaching the team to reduce technical debt. "
            "I introduced AI coding assistants (Cursor, Copilot) and shared best practices, significantly "
            "increasing productivity. At Walmart, I designed comprehensive JUnit and integration tests, "
            "setting patterns for testability. I create Confluence documentation to facilitate knowledge "
            "sharing and onboard new team members. I lead by writing clean, well-tested code and reviewing "
            "others' work constructively."
        ),
        (
            "5. Tell me about a failure or project that didn't go as planned. What did you learn?",
            "Early in a CI/CD rollout, we initially automated too many stages at once, which caused pipeline "
            "failures and delayed releases. I learned to incrementally automate-build first, then test, then "
            "deploy-and to add rollback mechanisms. I also learned the importance of SonarQube and quality gates "
            "early in the pipeline. Now I design pipelines with staged automation and clear ownership. The "
            "lesson: start small, validate each stage, and prioritize reliability over speed."
        ),
        (
            "6. Why Sigmoid? Why this role?",
            "I have nearly 10 years building scalable systems at Fortune 500 companies (Walmart, Dell, NCR) "
            "and hands-on experience with Spark, Kafka, and Scala from my work at UST Global. I want to deepen "
            "my focus on data engineering and distributed data platforms. Sigmoid's work with Honeywell, "
            "ironSource, and Capillary-delivering real-time insights through AI-driven systems-aligns with "
            "my experience in Kafka, Pub/Sub, and microservices. I thrive in high-ownership, fast-paced "
            "environments and am ready to lead technically while staying hands-on with production systems."
        ),
    ]
    for q, a in elaborated:
        story.append(Paragraph(f"<b>{q}</b>", styles["DocBody"]))
        story.append(Paragraph(a, styles["DocBody"]))
        story.append(Spacer(1, 10))

    story.append(Spacer(1, 8))
    story.append(Paragraph("Technical Topics to Review", styles["DocSubSectionHeader"]))

    story.append(Paragraph("Big Data & Distributed Systems", styles["DocBody"]))
    for item in [
        "Spark: RDD vs DataFrame vs Dataset, lazy evaluation, shuffle optimization, partitioning strategies, broadcast joins",
        "Hadoop: HDFS architecture, MapReduce flow, YARN, NameNode/DataNode",
        "Hive/Impala: Query optimization, partitioning, bucketing, metastore",
        "Kafka: Topics, partitions, consumer groups, exactly-once semantics, Kafka Streams vs Spark Streaming",
        "Data pipelines: Batch vs streaming, idempotency, exactly-once processing, backpressure",
    ]:
        story.append(Paragraph(f"&bull; {item}", styles["DocBullet"]))

    story.append(Paragraph("Backend (Java/Python/Scala)", styles["DocBody"]))
    for item in [
        "Concurrency, threading, async I/O",
        "API design (REST, gRPC), rate limiting, circuit breakers",
        "Object-oriented vs functional patterns (especially for Scala)",
    ]:
        story.append(Paragraph(f"&bull; {item}", styles["DocBullet"]))

    story.append(Paragraph("Cloud (GCP/AWS)", styles["DocBody"]))
    for item in [
        "GCP: BigQuery, Dataflow, Pub/Sub, Cloud Storage, Dataproc",
        "AWS: EMR, S3, Kinesis, Lambda, Glue",
        "Cost optimization, autoscaling, multi-region",
    ]:
        story.append(Paragraph(f"&bull; {item}", styles["DocBullet"]))

    story.append(Paragraph("System Design", styles["DocBody"]))
    for item in [
        "Designing a real-time analytics platform",
        "Designing a data lake vs data warehouse",
        "Handling massive datasets (partitioning, sharding, compaction)",
        "Fault tolerance, replication, consistency models (CAP, eventual consistency)",
    ]:
        story.append(Paragraph(f"&bull; {item}", styles["DocBullet"]))

    story.append(Paragraph("System Design Scenarios (Likely for This Role)", styles["DocSubSectionHeader"]))
    for i, scenario in enumerate([
        "Design a real-time analytics platform that ingests events from multiple sources and serves dashboards with sub-second latency.",
        "Design a data pipeline that processes billions of records daily with exactly-once semantics.",
        "Design a scalable data lake for a Fortune 500 company with diverse data sources.",
        "Design an API layer for a data platform that serves both batch and real-time queries.",
    ], 1):
        story.append(Paragraph(f"{i}. {scenario}", styles["DocBullet"]))

    story.append(Paragraph("Framework to use:", styles["DocBody"]))
    for item in [
        "Clarify requirements (throughput, latency, consistency, retention)",
        "High-level architecture (ingestion -> processing -> storage -> serving)",
        "Component choices (Kafka, Spark, Hive, etc.) with rationale",
        "Scaling, fault tolerance, monitoring",
        "Trade-offs and alternatives",
    ]:
        story.append(Paragraph(f"&bull; {item}", styles["DocBullet"]))

    story.append(PageBreak())

    # --- Section 2: Background Comparison ---
    story.append(Paragraph("2. Background Comparison (Your Profile)", styles["DocSectionHeader"]))
    story.append(Paragraph(
        "Tailored to your resume. Strengths to emphasize; gaps to address in interview.",
        styles["DocItalic"]
    ))
    comparison_data = [
        ["Requirement", "Rating", "Evidence"],
        ["10+ years software/data engineering", "4/5", "9.9 years (NCR, Walmart, Dell, SKIDATA, UST)"],
        ["Backend: Java/Python/Scala", "5/5", "Java, Spring Boot, Scala (UST); Scala/Spark/Kafka projects"],
        ["Enterprise-scale distributed systems", "4/5", "Microservices, K8s on GCP, Kafka, Pub/Sub at NCR/Walmart/Dell"],
        ["Hadoop, Spark, or similar", "4/5", "Spark, Kafka at UST; Kafka at NCR, Walmart, Dell"],
        ["Large/complex datasets", "3/5", "Spark/Kafka streaming at UST; ElasticSearch at Dell"],
        ["GCP or AWS", "4/5", "GCP (K8s, Pub/Sub) at NCR; Azure at Walmart"],
        ["Hadoop ecosystem (HDFS, Hive, Impala, Kafka)", "3/5", "Strong Kafka; limited HDFS/Hive/Impala"],
        ["Database modeling, warehousing", "4/5", "PostgreSQL, Oracle, Cassandra, AzureSQL, ElasticSearch"],
        ["Agile experience", "5/5", "Scrum/JIRA at Walmart; SDLC at Dell; Agile across roles"],
        ["Startup/high-growth environment", "3/5", "Fortune 500; NCR/Walmart/Dell scale"],
        ["Client-facing / stakeholder engagement", "3/5", "Confluence docs; internal stakeholders; room to grow"],
    ]
    story.append(create_table(comparison_data, col_widths=[2.5 * inch, 1.2 * inch, 3.3 * inch], styles=styles))
    story.append(Spacer(1, 16))

    # --- Section 3: Resume Tailoring Tips ---
    story.append(Paragraph("3. Resume Talking Points", styles["DocSectionHeader"]))
    story.append(Paragraph(
        "Emphasize these in your resume and interview:",
        styles["DocItalic"]
    ))
    story.append(Paragraph("Your strengths to highlight:", styles["DocSubSectionHeader"]))
    for item in [
        "~10 years at Fortune 500 (Walmart, Dell, NCR); scalable microservices, K8s, GCP",
        "Spark, Kafka, Scala at UST; Kafka across NCR, Walmart, Dell",
        "CI/CD architecture, SonarQube, Docker, Kubernetes on GCP",
        "PostgreSQL, Cassandra, ElasticSearch, AzureSQL, Oracle",
        "Agile/Scrum, JUnit, integration tests, Confluence documentation",
        "AI tools (Cursor, Copilot, Gemini) for productivity",
    ]:
        story.append(Paragraph(f"&bull; {item}", styles["DocBullet"]))

    story.append(Paragraph("Gaps to address (prepare to discuss):", styles["DocSubSectionHeader"]))
    for item in [
        "Hadoop ecosystem (HDFS, Hive, Impala): Brush up; emphasize Spark/Kafka transferability",
        "Direct client-facing / VP-level stakeholder work: Use internal stakeholder examples",
    ]:
        story.append(Paragraph(f"&bull; {item}", styles["DocBullet"]))
    story.append(Spacer(1, 12))

    # --- Section 4: Cover Letter ---
    story.append(Paragraph("4. Cover Letter (Tailored)", styles["DocSectionHeader"]))

    cover_letter = """Raghav Raj Singh
raghav.raj.singh30@gmail.com | +91-8210227760 | https://linkedin.com/in/raghavrajsingh/

[Date]

Hiring Manager
Sigmoid
Bangalore, India

Dear Hiring Manager,

I am writing to express my interest in the Technical Lead - Data Engineering position at Sigmoid. With nearly 10 years of experience building scalable distributed systems and backend platforms, I am excited about the opportunity to contribute to your mission of enabling Fortune 500 companies to unlock business value through data.

Your work with Honeywell, ironSource, and Capillary Technologies-delivering real-time insights through AI-driven systems-aligns closely with my experience. At UST Global, I worked on Scala, Spark, and Kafka for streaming and processing large datasets. At NCR Voyix, I architect microservices using Spring Boot, Kafka, and Google Cloud Pub/Sub, with containerized deployments on GCP and Kubernetes.

In my current role at NCR Voyix, I architected and implemented comprehensive CI/CD pipelines, automating build, test, and deployment stages to accelerate release cycles. I have hands-on expertise in Kafka, Spark, Java, and Scala, and cloud platforms including GCP and Azure. I configured SonarQube to enforce code quality and integrated AI-driven coding assistants to increase productivity. I am equally comfortable mentoring engineers through code reviews and documentation and translating business requirements into technical solutions.

What draws me to Sigmoid is the combination of high-ownership engineering culture, complex big data problems, and direct exposure to senior client stakeholders. I thrive in fast-paced, innovation-driven environments and am looking for a role where I can both lead technically and contribute hands-on to production-grade data platforms.

I would welcome the opportunity to discuss how my background in distributed systems and data engineering can support Sigmoid's growth and your clients' success. Thank you for considering my application.

Sincerely,

Raghav Raj Singh"""

    story.append(Paragraph(cover_letter.replace("\n", "<br/>"), styles["DocCode"]))

    story.append(Paragraph("Before sending: Add today's date; proofread; attach resume.", styles["DocItalic"]))

    story.append(Spacer(1, 16))

    # --- Next Steps ---
    story.append(Paragraph("Next Steps", styles["DocSectionHeader"]))
    for i, step in enumerate([
        "Practice the elaborated answers above; customize with specific metrics.",
        "Review system design scenarios (real-time analytics, data pipelines, data lake).",
        "Brush up on Hadoop ecosystem (HDFS, Hive, Impala) to address gaps.",
        "Research Sigmoid - website, LinkedIn, recent news, client case studies.",
    ], 1):
        story.append(Paragraph(f"{i}. {step}", styles["DocBullet"]))

    doc.build(story, onFirstPage=add_header_footer, onLaterPages=add_header_footer)
    print(f"PDF generated: {output_path}")
    return output_path


if __name__ == "__main__":
    main()
